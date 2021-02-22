package eg.gov.iti.server.db.helpers.adapters;

import eg.gov.iti.server.db.entities.Admin;
import eg.gov.iti.server.ui.models.AdminModel;

import java.util.ArrayList;
import java.util.List;

public class AdminAdapter {

    public static List<AdminModel> getAdminModelFromEntity(List<Admin> admins){
        List<AdminModel> adminModels = new ArrayList<>();
        for (Admin admin : admins){
            AdminModel adminModel = new AdminModel();
            adminModel = getAdminModel(admin);
            adminModels.add(adminModel);
        }
        return adminModels;
    }

    private static AdminModel getAdminModel(Admin admin){
        AdminModel adminModel = new AdminModel();
        adminModel.setAdminId(String.valueOf(admin.getAdminId()));
        adminModel.setUserName(admin.getAdminUserName());
        adminModel.setPhoneNumber(admin.getAdminPhoneNumber());
        return adminModel;
    }
}
