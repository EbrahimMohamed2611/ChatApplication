package eg.gov.iti.server.net.callbackConfiguration;

import eg.gov.iti.contract.client.ChatClient;

import java.util.HashMap;
import java.util.Map;

public class OnlineClients {
    private Map<String, ChatClient> onlineClients = new HashMap<>();
    private static OnlineClients onlineClientsInstance;
    private OnlineClients(){

    }

    public static OnlineClients getInstance() {
        if (onlineClientsInstance == null) {
            onlineClientsInstance = new OnlineClients();
        }
        return onlineClientsInstance;
    }

    public Map<String, ChatClient> getOnlineClients() {
        return onlineClients;
    }
}
