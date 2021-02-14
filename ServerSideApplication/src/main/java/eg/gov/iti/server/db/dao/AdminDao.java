package eg.gov.iti.server.db.dao;

public interface AdminDao {
    Boolean isExisted(String phoneNumber);
    Boolean isPasswordValid(String phoneNumber, String enteredPassword);
}
