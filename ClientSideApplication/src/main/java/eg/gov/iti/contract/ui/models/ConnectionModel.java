package eg.gov.iti.contract.ui.models;

public class ConnectionModel {
    private String serverIp;
    private static ConnectionModel instance = null;

    private ConnectionModel() {
    }

    public static ConnectionModel getInstance() {
        if (instance == null)
            instance = new ConnectionModel();

        return instance;
    }


    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
}
