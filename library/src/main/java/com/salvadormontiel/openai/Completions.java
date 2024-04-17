package com.salvadormontiel.openai;

import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.output.ChatCompletionOutput;
import com.salvadormontiel.openai.response.ChatCompletion;
import com.salvadormontiel.openai.response.ChatCompletionChunk;
import com.salvadormontiel.openai.utils.ResponseBodyPublisher;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Flow;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;
import static com.salvadormontiel.openai.utils.Jsonable.toJson;

public class Completions {
    private final String apiKey;

    Completions(String apiKey) {
        this.apiKey = apiKey;
    }

    public ChatCompletionOutput create(ChatCompletionInput input) throws URISyntaxException, IOException, InterruptedException {
        String json = toJson(input);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openai.com/v1/chat/completions"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject responseJson = new JSONObject(response.body());
//        String res = responseJson.getJSONArray("choices")
//                .getJSONObject(0)
//                .getJSONObject("message")
//                .getString("content");
//        System.out.println();
        return fromJson(response.body(), ChatCompletionOutput.class);
    }

    public ChatCompletion createAsStream(ChatCompletionInput input) throws URISyntaxException, IOException, InterruptedException {
        // input.stream check if it's true
        String json = toJson(input);
        System.out.println("'"+apiKey+"'");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.openai.com/v1/chat/completions"))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .build();

        final CountDownLatch latch = new CountDownLatch(1);
/*
        Flow.Publisher<List<ByteBuffer>> publisher = ResponseBodyPublisher.createPublisher(httpClient, request);
        publisher.subscribe(new Flow.Subscriber<List<ByteBuffer>>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(List<ByteBuffer> buffers) {
                // Process each ByteBuffer in the list
                for (ByteBuffer buffer : buffers) {
                    //System.out.println("Received ByteBuffer with remaining: " + buffer.remaining());
                    // Process the buffer here
                    System.out.println("<" + StandardCharsets.UTF_8.decode(buffer) + ">");
                }
                subscription.request(1); // Request the next list of ByteBuffers

                System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                latch.countDown();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed!");
                latch.countDown();
            }
        });*/


        Flow.Publisher<ChatCompletionChunk> publisher = ResponseBodyPublisher.createPublisher(httpClient, request);
        publisher.subscribe(new Flow.Subscriber<>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(ChatCompletionChunk chunk) {
                System.out.println(chunk);
                System.out.println();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                latch.countDown();
            }

            @Override
            public void onComplete() {
                latch.countDown();
            }
        });
        latch.await();


        /*HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        try (InputStream is = response.body()) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = is.read(buffer)) != -1) {
                System.out.println("Read chunk of size: " + read);
                System.out.println(new String(buffer, 0, read));
            }
        }
        JSONObject responseJson = new JSONObject(response.body());*/
        System.out.println();

        return null;
    }
}
