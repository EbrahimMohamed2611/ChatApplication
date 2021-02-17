package eg.gov.iti.server.net.serverConfiguration.chatRemoteInterfaceImpl;

import eg.gov.iti.contract.clientServerDTO.dto.Contact;
import eg.gov.iti.contract.server.chatRemoteInterfaces.contactService;
import eg.gov.iti.server.db.dao.ContactDao;
import eg.gov.iti.server.db.dao.UserDao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ContactServiceImpl extends UnicastRemoteObject implements contactService {

    ContactDao contactDao;
    UserDao userDao;

    protected ContactServiceImpl() throws RemoteException {
    }

    @Override
    public int addNewContact(Contact contact) {

        if(contactDao.getContact(contact.getUserPhoneNumber(),contact.getContactPhoneNumber())!=null){
            return 0;
        }
        return contactDao.insertContact(contact);
    }

    @Override
    public List<Contact> getUserContacts(String userPhone) {
        return contactDao.getContacts(userPhone);
    }
}

