package eg.gov.iti.server.db.dao;

import eg.gov.iti.server.db.entities.Admin;

import java.util.List;

public interface AdminDao {
    Boolean isExisted(String phoneNumber);
    Boolean isPasswordValid(String phoneNumber, String enteredPassword);
    List<Admin> getAllAdmins();
}
