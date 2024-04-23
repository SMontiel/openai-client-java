package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.TranscriptionInput;
import com.salvadormontiel.openai.response.Transcription;
import com.salvadormontiel.openai.utils.MultipartRequest;
import com.salvadormontiel.openai.utils.Pair;

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
import static com.salvadormontiel.openai.utils.Validate.notNull;

public class Transcriptions {
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newBuilder().build();

    public Transcriptions(String apiKey) {
        this.apiKey = notNull(apiKey);
    }

    public Transcription create(TranscriptionInput input) {
        HttpRequest request = getRequest(input.toMultiMap());

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return fromJson(response.body(), Transcription.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest getRequest(List<Pair<String, Object>> data) {
        try {
            String boundary = new BigInteger(256, new Random()).toString();

            return HttpRequest.newBuilder()
                    .uri(new URI("https://api.openai.com/v1/audio/transcriptions"))
                    .POST(MultipartRequest.from(data, boundary))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "multipart/form-data;boundary=" + boundary)
                    .build();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
