package com.salvadormontiel.openai.utils;

import com.salvadormontiel.openai.input.ChatCompletionInput;
import com.salvadormontiel.openai.response.ChatCompletionChunk;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.net.http.HttpClient;

import static com.salvadormontiel.openai.utils.Jsonable.fromJson;

public class ResponseBodyPublisher {

    public static Flow.Publisher<List<ByteBuffer>> __createPublisher(HttpClient client, HttpRequest request) {
        SubmissionPublisher<List<ByteBuffer>> publisher = new SubmissionPublisher<>();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                .thenAccept(response -> {
                    response.body().subscribe(new Flow.Subscriber<List<ByteBuffer>>() {
                        private Flow.Subscription subscription;

                        @Override
                        public void onSubscribe(Flow.Subscription subscription) {
                            this.subscription = subscription;
                            subscription.request(1); // Request the first item
                        }

                        @Override
                        public void onNext(List<ByteBuffer> item) {
                            publisher.submit(item); // Emit the item (List<ByteBuffer>) to subscribers
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
                })
                .exceptionally(throwable -> {
                    publisher.closeExceptionally(throwable);
                    return null;
                });

        return publisher;
    }

    public static Flow.Publisher<List<String>> _createPublisher(HttpClient client, HttpRequest request) {
        SubmissionPublisher<List<String>> publisher = new SubmissionPublisher<>();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                .thenAccept(response -> {
                    response.body().subscribe(new Flow.Subscriber<List<ByteBuffer>>() {
                        private Flow.Subscription subscription;

                        @Override
                        public void onSubscribe(Flow.Subscription subscription) {
                            this.subscription = subscription;
                            subscription.request(1); // Request the first item
                        }

                        @Override
                        public void onNext(List<ByteBuffer> buffers) {
                            List<String> list = new ArrayList<>(buffers.size());

                            for (ByteBuffer buffer : buffers) {
                                String[] lines = StandardCharsets.UTF_8.decode(buffer).toString()
                                        .split("\n");
                                for (String line : lines) {
                                    if (line.isEmpty()) continue;
                                    if (line.equals("data: [DONE]")) continue;
                                    if (!line.startsWith("data:")) continue;

                                    list.add(line.substring(6));
                                }
                            }

                            publisher.submit(list); // Emit the item (List<ByteBuffer>) to subscribers
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
                })
                .exceptionally(throwable -> {
                    publisher.closeExceptionally(throwable);
                    return null;
                });

        return publisher;
    }

    public static Flow.Publisher<ChatCompletionChunk> createPublisher(HttpClient client, HttpRequest request) {
        SubmissionPublisher<ChatCompletionChunk> publisher = new SubmissionPublisher<>();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofPublisher())
                .thenAccept(response -> {
                    response.body().subscribe(new Flow.Subscriber<List<ByteBuffer>>() {
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
                })
                .exceptionally(throwable -> {
                    publisher.closeExceptionally(throwable);
                    return null;
                });

        return publisher;
    }
}
