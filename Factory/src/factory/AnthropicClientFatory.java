package factory;

import chatClients.AiChatClient;
import chatClients.AnthropicChatClient;
import vectorClients.AiVectorClient;
import vectorClients.AnthropicVectorClient;

public class AnthropicClientFatory implements AiClientFactory{
    @Override
    public AiChatClient getAiChatClient() {
        return new AnthropicChatClient();
    }

    @Override
    public AiVectorClient getAiVectorClient() {
        return new AnthropicVectorClient();
    }
}
