module ServerSideApplication {
    // For Database Connection
    requires java.naming;
    requires java.sql;
    requires mysql.connector.java;

    //For RMI
    requires java.rmi;

    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires ClientServerContract;
    requires com.jfoenix;


    exports eg.gov.iti.server;
    exports eg.gov.iti.server.ui.controllers.loginControllers;
    exports eg.gov.iti.server.ui.controllers;
    exports eg.gov.iti.server.db.dao;

    opens eg.gov.iti.server.ui.controllers.loginControllers;
    opens eg.gov.iti.server.ui.controllers;
    exports eg.gov.iti.server.db.entities;
    opens eg.gov.iti.server.ui.models;
    opens eg.gov.iti.server.db.entities;


}