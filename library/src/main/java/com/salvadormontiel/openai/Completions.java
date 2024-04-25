package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.input.ChatMessage;
import com.salvadormontiel.openai.input.ToolCall;
import com.salvadormontiel.openai.response.ChatCompletion;
import com.salvadormontiel.openai.response.ChatCompletionChunk;
import com.salvadormontiel.openai.response.Choice;
import com.salvadormontiel.openai.utils.ResponseBodyPublisher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Function;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;
import static com.salvadormontiel.openai.utils.Jsonable.toJson;

public class Completions {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    Completions(String apiKey) {
        this.apiKey = apiKey;
    }

    public ChatCompletion create(ChatCompletionInput input) {
        return create(input, new FunctionExecutor());
    }

    public ChatCompletion create(ChatCompletionInput input, FunctionExecutor executor) {
        if (input.stream) {
            throw new RuntimeException("The 'stream' field must be false, use the createAsStream() function if you want to get a stream");
        }

        HttpRequest request = getRequest(toJson(input));

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        ChatCompletion completion = fromJson(response.body(), ChatCompletion.class);

        Choice choice = completion.choices.get(0);
        if (choice.finishReason != null && choice.finishReason.equals("stop")) {
            return completion;
        }
        ChatMessage responseMessage = choice.message;
        if (!responseMessage.toolCalls.isEmpty()) {
            List<ChatMessage> messages = input.messages;
            messages.add(responseMessage);

            List<ToolCall> toolCalls = responseMessage.toolCalls;
            toolCalls.forEach(toolCall -> {
                String functionName = toolCall.function.name;
                String args = toolCall.function.arguments;
                Function<FunctionProperties, String> callable = executor.getFunction(functionName);
                if (callable == null) {
                    return;
                }
                FunctionProperties fp = fromJson(args, executor.getClass(functionName));

                // TODO: Change response type of callable from String to Object,
                //  then parse to JSON the output, if not a string
                String functionResponse = callable.apply(fp);

                ChatMessage cm = new ChatMessage("tool", functionName, functionResponse, null, toolCall.id);
                messages.add(cm);
            });

            input.messages = messages;

            completion = create(input, executor);
        }

        return completion;
    }

    public Flow.Publisher<ChatCompletionChunk> createAsStream(ChatCompletionInput input) {
        if (!input.stream) {
            throw new RuntimeException("The 'stream' field must be true, use the create() function otherwise");
        }

        HttpRequest request = getRequest(toJson(input));

        return ResponseBodyPublisher.createPublisher(
                httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
        );
    }

    private HttpRequest getRequest(String json) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/chat/completions"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
