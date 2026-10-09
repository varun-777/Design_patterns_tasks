package factory;

import chatClients.AiChatClient;
import chatClients.OpenAiChatClient;
import vectorClients.AiVectorClient;
import vectorClients.OpenAiVectorClient;

public class OpenAiClientFactory implements AiClientFactory{
    @Override
    public AiChatClient getAiChatClient() {
        return new OpenAiChatClient();
    }

    @Override
    public AiVectorClient getAiVectorClient() {
        return new OpenAiVectorClient();
    }
}
