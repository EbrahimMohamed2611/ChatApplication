package eg.gov.iti.contract.ui.helpers;

import eg.gov.iti.contract.net.ServicesLocator;
import eg.gov.iti.contract.net.adapters.UserAuthAdapter;
import eg.gov.iti.contract.server.chatRemoteInterfaces.LoginServiceInterface;
import eg.gov.iti.contract.ui.models.CachedData;
import eg.gov.iti.contract.ui.models.UserAuthModel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

public class CachedCredentialsData {
    private CachedData cachedData;
    private static CachedCredentialsData credentialsDataInstance;
    LoginServiceInterface loginService;
    ModelsFactory modelsFactory;
    UserAuthModel userAuthModel;

    private CachedCredentialsData() {
        loginService = ServicesLocator.getLoginService();
        modelsFactory = ModelsFactory.getInstance();
        userAuthModel = modelsFactory.getAuthUserModel();
    }

    public static CachedCredentialsData getInstance() {
        if (credentialsDataInstance == null) {
            credentialsDataInstance = new CachedCredentialsData();
        }
        return credentialsDataInstance;
    }

    public CachedData saveCredentials(UserAuthModel userAuthModel) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CachedData.class);
            cachedData = new CachedData(userAuthModel.getPhoneNumber(), userAuthModel.getPassword());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cachedData, new FileWriter("credentials.xml"));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return cachedData;
    }

    public boolean validateCredentials() {
        try {
            File file = new File("credentials.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(CachedData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            CachedData cachedData = (CachedData) jaxbUnmarshaller.unmarshal(file);
            userAuthModel.setPhoneNumber(cachedData.getPhoneNumber());
            userAuthModel.setPassword(cachedData.getPassword());

            try {
                if (loginService.checkPhoneNumber(UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))) {
                    if (loginService.checkPassword(UserAuthAdapter.getUserAuthDtoFromModelAdapter(userAuthModel))){
                        return true;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clearCredentials() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CachedData.class);
            cachedData = new CachedData("", "");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(cachedData, new FileWriter("credentials.xml"));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
