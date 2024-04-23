package com.salvadormontiel.openai.utils;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MultipartRequest {

    public static HttpRequest.BodyPublisher from(List<Pair<String, Object>> data, String boundary) throws IOException {
        List<byte[]> byteArrays = new ArrayList<>();
        byte[] separator = ("\r\n--" + boundary + "\r\nContent-Disposition: form-data; name=")
                .getBytes(StandardCharsets.UTF_8);

        for (Pair<String, Object> entry : data) {
            byteArrays.add(separator);

            if (entry.second instanceof Path) {
                Path path = (Path) entry.second;
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.first + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n")
                        .getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Files.readAllBytes(path));
            } else {
                byteArrays.add(("\"" + entry.first + "\"\r\n\r\n" + entry.second)
                        .getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays.add(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }
}
