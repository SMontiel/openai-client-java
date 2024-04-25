package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.ImageEditInput;
import com.salvadormontiel.openai.input.ImageInput;
import com.salvadormontiel.openai.response.ImageResponse;
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

    public ImageResponse edit(ImageEditInput input) {
        HttpRequest request = getMultiPartRequest("https://api.openai.com/v1/images/edits", input.toMultiMap());

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

    private HttpRequest getMultiPartRequest(String url, List<Pair<String, Object>> data) {
        try {
            String boundary = new BigInteger(256, new Random()).toString();

            return HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(MultipartRequest.from(data, boundary))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "multipart/form-data;boundary=" + boundary)
                    .build();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
