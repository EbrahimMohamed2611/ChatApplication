package eg.gov.iti.server.ui.helpers;


import eg.gov.iti.server.ui.models.AdminAuthentication;

public class ModelsFactory {

    private static final ModelsFactory instance = new ModelsFactory();

    private AdminAuthentication adminAuthModel;

    private ModelsFactory() { }

    public static ModelsFactory getInstance() {
        return instance;
    }

    public AdminAuthentication getAuthAdminModel() {
        if (adminAuthModel == null) {
            adminAuthModel = new AdminAuthentication();
        }
        return adminAuthModel;
    }

}
