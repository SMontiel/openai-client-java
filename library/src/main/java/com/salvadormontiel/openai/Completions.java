package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.response.ChatCompletion;
import com.salvadormontiel.openai.response.ChatCompletionChunk;
import com.salvadormontiel.openai.utils.ResponseBodyPublisher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Flow;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;
import static com.salvadormontiel.openai.utils.Jsonable.toJson;

public class Completions {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    Completions(String apiKey) {
        this.apiKey = apiKey;
    }

    public ChatCompletion create(ChatCompletionInput input) {
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

        return fromJson(response.body(), ChatCompletion.class);
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
