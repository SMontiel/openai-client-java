package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.EmbeddingsInput;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;
import static com.salvadormontiel.openai.utils.Jsonable.toJson;
import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Embeddings {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public Embeddings(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public com.salvadormontiel.openai.response.Embeddings create(EmbeddingsInput input) {
        HttpRequest request = getRequest(toJson(input));

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return fromJson(response.body(), com.salvadormontiel.openai.response.Embeddings.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequest(String json) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/embeddings"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
