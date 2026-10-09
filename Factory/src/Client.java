import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select the provider");
        String providerName = sc.next();

        ChatService chatService = new ChatService(providerName);
        //
        // if(providerName.equals("openai")){
        // chatService = new ChatService(new chatClients.OpenAiChatClient());
        // }
        // else if(providerName.equals("anthropic")){
        // chatService = new ChatService(new chatClients.AnthropicChatClient());
        // }

        System.out.println("Please give the prompt");
        String prompt = sc.next();
        chatService.chat(prompt);
    }
}
