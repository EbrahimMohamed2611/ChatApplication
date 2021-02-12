package eg.gov.iti.contract.ui.helpers;


import eg.gov.iti.contract.ui.models.UserAuthModel;

public class ModelsFactory {

    private static final ModelsFactory instance = new ModelsFactory();

    private UserAuthModel userAuthModel;

    private ModelsFactory () { }

    public static ModelsFactory getInstance() {
        return instance;
    }

    public UserAuthModel getCurrentUserModel() {
        if (userAuthModel == null) {
            userAuthModel = new UserAuthModel();
        }
        return userAuthModel;
    }

}
