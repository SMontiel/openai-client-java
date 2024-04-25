package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.ImageInput;
import com.salvadormontiel.openai.response.ImageResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;
import static com.salvadormontiel.openai.utils.Jsonable.toJson;
import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Images {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public Images(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public ImageResponse generate(ImageInput input) {
        HttpRequest request = getRequest(toJson(input));

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return fromJson(response.body(), ImageResponse.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequest(String json) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/images/generations"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
