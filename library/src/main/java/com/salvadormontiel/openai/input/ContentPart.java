package com.salvadormontiel.openai.input;

public class ContentPart {
    public String type;
    public String text;
    public String imageUrl;

    private ContentPart(String type, String text, String imageUrl) {
        this.type = type;
        this.text = text;
        this.imageUrl = imageUrl;
    }

    public static ContentPart asText(String text) {
        return new ContentPart("text", text, null);
    }

    public static ContentPart asImageUrl(String imageUrl) {
        return new ContentPart("image_url", null, imageUrl);
    }

    @Override
    public String toString() {
        return "ContentPart{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
