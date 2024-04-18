package com.salvadormontiel.openai;

import com.salvadormontiel.openai.response.ModelsOutput;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;

public class Models {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    Models(String apiKey) {
        this.apiKey = apiKey;
    }

    public ModelsOutput list() {
        HttpResponse<String> response;
        try {
            response = httpClient.send(getRequest(), HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return fromJson(response.body(), ModelsOutput.class);
    }

    private HttpRequest getRequest() {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/models"))
                    .GET()
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
