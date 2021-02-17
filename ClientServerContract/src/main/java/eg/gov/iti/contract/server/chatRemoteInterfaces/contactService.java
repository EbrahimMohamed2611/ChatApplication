package eg.gov.iti.contract.server.chatRemoteInterfaces;

import eg.gov.iti.contract.clientServerDTO.dto.Contact;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface contactService extends Remote {

    int addNewContact(Contact contact) throws RemoteException;
    List<Contact> getUserContacts(String userPhone) throws RemoteException;
}

