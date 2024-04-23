package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.SpeechInput;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.salvadormontiel.openai.utils.Jsonable.toJson;
import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Speech {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public Speech(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public Path create(SpeechInput input) {
        System.out.println(input);

        HttpRequest request = getRequest(toJson(input));
        // Unique file name
        Path file = Paths.get("/tmp/speech-" + System.currentTimeMillis() + "-" + input.input.hashCode()  + "." + input.responseFormat);

        try {
            HttpResponse<Path> response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(file));
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequest(String json) {
        try {
            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/audio/speech"))
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
