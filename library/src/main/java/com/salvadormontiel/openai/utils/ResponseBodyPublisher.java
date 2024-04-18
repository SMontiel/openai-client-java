package com.salvadormontiel.openai.utils;

import com.salvadormontiel.openai.response.ChatCompletionChunk;

import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;

public class ResponseBodyPublisher {

    public static Flow.Publisher<ChatCompletionChunk> createPublisher(CompletableFuture<HttpResponse<Flow.Publisher<List<ByteBuffer>>>> future) {
        SubmissionPublisher<ChatCompletionChunk> publisher = new SubmissionPublisher<>();

        future.thenAccept(response -> {
            response.body().subscribe(new Flow.Subscriber<>() {
                private Flow.Subscription subscription;

                @Override
                public void onSubscribe(Flow.Subscription subscription) {
                    this.subscription = subscription;
                    subscription.request(1); // Request the first item
                }

                @Override
                public void onNext(List<ByteBuffer> buffers) {
                    for (ByteBuffer buffer : buffers) {
                        String[] lines = StandardCharsets.UTF_8.decode(buffer).toString()
                                .split("\n");
                        for (String line : lines) {
                            if (line.isEmpty()) continue;
                            if (line.equals("data: [DONE]")) continue;
                            if (!line.startsWith("data:")) continue;

                            String json = line.substring(6);

                            ChatCompletionChunk chunk = fromJson(json, ChatCompletionChunk.class);
                            publisher.submit(chunk);
                        }
                    }

                    subscription.request(1); // Request the next item
                }

                @Override
                public void onError(Throwable throwable) {
                    publisher.closeExceptionally(throwable);
                }

                @Override
                public void onComplete() {
                    publisher.close();
                }
            });
        }).exceptionally(throwable -> {
            publisher.closeExceptionally(throwable);
            return null;
        });

        return publisher;
    }
}
