package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.input.ChatMessage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("Hello and welcome!");
        System.out.println(DotEnv.get("OPENAI_API_KEY"));

        OpenAI client = new OpenAI(DotEnv.get("OPENAI_API_KEY"));
        var messages = new ArrayList<ChatMessage>();
        messages.add(
                new ChatMessage.Builder()
                        .asSystemRole()
                        .setContent("You are a helpful assistant.")
                        .build()
        );
        messages.add(
                new ChatMessage.Builder()
                        .asUserRole()
                        .setContent("Hello!")
                        .build()
        );
        //System.out.println(messages);
        var input = new ChatCompletionInput.Builder()
                .setModel("gpt-3.5-turbo")
                .setMessages(messages)
                .setStream(true)
                .build();
        System.out.println(input);

        //var completion = client.chat().completions().create(input);
        var completion = client.chat().completions().createAsStream(input);

        System.out.println(">>>>>>>>>>>>>>>>>>><");
        //System.out.println(completion.choices.get(0).message.content);
        //System.out.println(completion.usage);
/*
        var system = new ChatMessage.Builder()
                .asSystemRole()
                .setContent("aa")
                .setName("system")
                .build();
        System.out.println(system);

        var user = new ChatMessage.Builder()
                .asUserRole()
                .setContent("aa")
                .setName("user")
                .build();
        System.out.println(user);

        var userParts = new ChatMessage.Builder()
                .asUserRole()
                .addTextContentPart("long description")
                .addImageUrlContentPart("https://example.com/logo.svg")
                //.setContent("")
                .setName("user parts")
                .build();
        System.out.println(userParts);

        var assistant = new ChatMessage.Builder()
                .asAssistantRole()
                .setContent("aa")
                .addToolCall(new ToolCall.Builder().setId("1").setType("ww").setFunction("a", "b").build())
                .setName("assistant")
                .build();
        System.out.println(assistant);

        var tool = new ChatMessage.Builder()
                .asToolRole()
                .setContent("aa")
                .setToolCallId("assistant")
                .build();
        System.out.println(tool);

        var request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();
        var httpClient = HttpClient.newBuilder()
                .build();
        var response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println(response.body());

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
            String greeting = Client.getGreeting("John " + i);
            System.out.println(greeting);
        }*/
    }
}
