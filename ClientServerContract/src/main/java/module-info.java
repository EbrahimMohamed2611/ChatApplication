module ClientServerContract {

    // For Database Connection
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;

    //For RMI
    requires java.rmi;

    requires transitive javafx.controls;
    requires transitive javafx.fxml;


    exports eg.gov.iti.contract.clientServerDTO.dto;
    exports eg.gov.iti.contract.server.chatRemoteInterfaces;
    exports eg.gov.iti.contract.client;
}