package eg.gov.iti.server.ui.helpers;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

import java.io.File;

public class ChatBot {
    private String botsPath = "E:\\crud\\chat\\ChatApplication\\ServerSideApplication\\src\\main\\resources";

    Bot superBot = new Bot("super",botsPath);
    Chat chat = new Chat(superBot);

    public ChatBot() {}

    public String sendBotMsg(String msg) {

        String botMsg = chat.multisentenceRespond(msg);
        if (!botMsg.equals("I have no answer for that.")) {
            return botMsg;
        }
        return "I have no answer";

    }

}
