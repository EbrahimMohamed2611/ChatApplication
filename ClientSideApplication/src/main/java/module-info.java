module ClientSideApplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    //For RMI
    requires java.rmi;
    requires com.jfoenix;
    requires org.kordamp.ikonli.materialdesign2;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;


    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.fontawesome5;


    requires ClientServerContract;
    requires java.sql;
//    exports eg.gov.iti.contract;

//    requires eg.gov.iti.contract.clientServerDTO.dto;
//    requires eg.gov.iti.contract.server.chatRemoteInterfaces;
//    requires eg.gov.iti.contract.client;

    exports eg.gov.iti.contract.ui.controllers.registerControllers;
    opens eg.gov.iti.contract.ui.controllers.registerControllers to javafx.fxml;

        exports eg.gov.iti.contract;
    exports eg.gov.iti.contract.ui.controllers.loginControllers;
    opens eg.gov.iti.contract.ui.controllers.loginControllers;
    opens eg.gov.iti.contract.ui.controllers;
    exports eg.gov.iti.contract.ui.controllers;
    exports eg.gov.iti.contract.net;
}