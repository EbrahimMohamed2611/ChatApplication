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


    exports eg.gov.iti.contract;

}