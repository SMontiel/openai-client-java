package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.input.ChatMessage;
import com.salvadormontiel.openai.input.ToolCall;
import com.salvadormontiel.openai.response.ChatCompletionChunk;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;

public class ChatCompletionSample {
    public static OpenAI getClient() {
        return new OpenAI(DotEnv.get("OPENAI_API_KEY"));
    }

    public static void sync() {
        var completion = getClient().chat().completions().create(getInputBuilder().build());
        System.out.println(completion.choices.get(0).message.content);
    }

    public static void asyncStream() {
        var completion2 = getClient().chat().completions().createAsStream(
                getInputBuilder().setStream(true).build()
        );

        final CountDownLatch latch = new CountDownLatch(1);
        completion2.subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(ChatCompletionChunk chunk) {
                String content = chunk.choices.get(0).delta.content;
                if (content != null) System.out.print(content);

                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println();

                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static ChatCompletionInput.Builder getInputBuilder() {
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
                        .setContent("count to 100, each number in a new line")
                        //.setContent("Hello! How can I write a Python script to sum two numbers and double the result")
                        .build()
        );

        return new ChatCompletionInput.Builder()
                .setModel("gpt-3.5-turbo")
                .setMessages(messages)
                .setStream(false);
    }

    public static void usingChatMessageBuilders() {
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
    }
}
