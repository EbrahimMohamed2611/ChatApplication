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


    exports eg.gov.iti.server;
    exports eg.gov.iti.server.ui.controllers.loginControllers;
    exports eg.gov.iti.server.ui.controllers;

    opens eg.gov.iti.server.ui.controllers.loginControllers;
}