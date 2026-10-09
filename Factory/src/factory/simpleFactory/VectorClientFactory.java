package factory.simpleFactory;

import vectorClients.AiVectorClient;
import vectorClients.AnthropicVectorClient;
import vectorClients.OpenAiVectorClient;

public class VectorClientFactory {
    public static AiVectorClient getAiVectorClient(String providerName){
        if(providerName.equals("openai")){
            return new OpenAiVectorClient();
        }
        else if(providerName.equals("anthropic")){
            return new AnthropicVectorClient();
        }
        throw new RuntimeException("Invalid prompt");
    }
}
