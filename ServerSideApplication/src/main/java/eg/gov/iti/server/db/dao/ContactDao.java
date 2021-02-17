package eg.gov.iti.server.db.dao;

import eg.gov.iti.contract.clientServerDTO.dto.Contact;

import java.util.List;

public interface ContactDao {

    int insertContact(Contact contact);
    Contact getContact(String userPhone,String contactPhone);
    List<Contact> getContacts(String userPhone);
}
