import chatClients.AiChatClient;
import factory.AiClientFactory;
import factory.ClientFactoryProvider;
import factory.simpleFactory.ChatClientFactory;
import factory.simpleFactory.VectorClientFactory;
import vectorClients.AiVectorClient;

public class ChatService {
    //private chatClients.OpenAiChatClient openAiChatClient;

    private AiChatClient aiChatClient;
    private AiVectorClient aiVectorClient;
    private AiClientFactory aiClientFactory;
    public ChatService(String providerName){
       // this.aiChatClient = aiChatClient;
        this.aiClientFactory = ClientFactoryProvider.getAiClientFactory(providerName);
        this.aiChatClient = aiClientFactory.getAiChatClient();
        this.aiVectorClient = aiClientFactory.getAiVectorClient();
    }

    public void chat(String prompt){
        aiChatClient.chat(prompt);
    }
}
