package factory;

public class ClientFactoryProvider {
    public static AiClientFactory getAiClientFactory(String provider){
        if(provider.equals("openai")){
            return new OpenAiClientFactory();
        }
        else if(provider.equals("anthropic")){
            return new AnthropicClientFatory();
        }
        return null;
    }
}
