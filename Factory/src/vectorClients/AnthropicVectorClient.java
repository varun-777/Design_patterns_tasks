package vectorClients;

public class AnthropicVectorClient implements AiVectorClient {
    @Override
    public void embed(String prompt) {
        System.out.println("Vector embedding from anthropic AI");
    }
}
