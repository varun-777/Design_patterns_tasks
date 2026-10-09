package vectorClients;

public class OpenAiVectorClient implements AiVectorClient {
    @Override
    public void embed(String prompt) {
        System.out.println("Vector embedding from open AI");
    }

}
