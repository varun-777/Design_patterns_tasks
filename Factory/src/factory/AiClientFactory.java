package factory;

import chatClients.AiChatClient;
import vectorClients.AiVectorClient;

public interface AiClientFactory {
    AiChatClient getAiChatClient();
    AiVectorClient getAiVectorClient();

}
