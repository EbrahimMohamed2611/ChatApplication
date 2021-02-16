package eg.gov.iti.contract.ui.helpers;


import eg.gov.iti.contract.ui.models.UserAuthModel;
import eg.gov.iti.contract.ui.models.UserRegisterModel;

public class ModelsFactory {

    private static final ModelsFactory instance = new ModelsFactory();

    private UserAuthModel userAuthModel;
    private UserRegisterModel userRegisterModel;


    private ModelsFactory () { }

    public static ModelsFactory getInstance() {
        return instance;
    }

    public UserAuthModel getAuthUserModel() {
        if (userAuthModel == null) {
            userAuthModel = new UserAuthModel();
        }
        return userAuthModel;
    }

    public UserRegisterModel getRegisterUserModel() {
        if (userRegisterModel == null) {
            userRegisterModel = new UserRegisterModel();
        }
        return userRegisterModel;
    }
}
