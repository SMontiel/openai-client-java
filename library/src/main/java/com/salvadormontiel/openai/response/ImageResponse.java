package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

import java.util.List;

public class ImageResponse {
    public int created;
    public List<Image> data;

    public ImageResponse(int created, List<Image> data) {
        this.created = created;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "created=" + created +
                ", data=" + data +
                '}';
    }

    public static class Image {
        @Json(name = "revised_prompt") public String revisedPrompt;
        public String url;
        @Json(name = "b64_json") public String b64Json;

        public Image(String revisedPrompt, String url, String b64Json) {
            this.revisedPrompt = revisedPrompt;
            this.url = url;
            this.b64Json = b64Json;
        }

        @Override
        public String toString() {
            return "Image{" +
                    "revisedPrompt='" + revisedPrompt + '\'' +
                    ", url='" + url + '\'' +
                    ", b64Json='" + b64Json + '\'' +
                    '}';
        }
    }
}
