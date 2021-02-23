package eg.gov.iti.contract.ui.helpers;


import eg.gov.iti.contract.ui.models.*;

public class ModelsFactory {

    private static final ModelsFactory instance = new ModelsFactory();

    private UserAuthModel userAuthModel;
    private UserRegisterModel userRegisterModel;
    private UserInvitationModel userInvitationModel;
    private ConnectionModel connectionModel;

    private CurrentUserModel currentUserModel;

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

    public UserInvitationModel getUserInvitationModel() {
        if (userInvitationModel == null) {
            userInvitationModel = new UserInvitationModel();
        }
        return userInvitationModel;
    }
    public CurrentUserModel getCurrentUserModel() {
        if (currentUserModel == null) {
//            currentUserModel = new CurrentUserModel();
            currentUserModel = CurrentUserModel.getInstance();
        }
        return currentUserModel;
    }

    public ConnectionModel getConnectionModel(){
        if (connectionModel == null){
            connectionModel = ConnectionModel.getInstance();
        }
        return connectionModel;
    }
}
