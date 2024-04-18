package com.salvadormontiel.openai.input;

import com.squareup.moshi.Json;

import static com.salvadormontiel.openai.utils.Validate.notNull;

public class SpeechInput {
    public String model;
    public String input;
    public String voice;
    @Json(name = "response_format") public String responseFormat;
    public double speed;

    private SpeechInput(String model, String input, String voice, String responseFormat, double speed) {
        this.model = notNull(model);
        this.input = notNull(input);
        this.voice = notNull(voice);
        this.responseFormat = responseFormat;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "SpeechInput{" +
                "model='" + model + '\'' +
                ", input='" + input + '\'' +
                ", voice='" + voice + '\'' +
                ", responseFormat='" + responseFormat + '\'' +
                ", speed=" + speed +
                '}';
    }

    public static class Builder {
        private String model;
        private String input;
        private String voice;
        private String responseFormat = "mp3";
        private double speed = 1;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setInput(String input) {
            this.input = input;
            return this;
        }

        public Builder setVoice(String voice) {
            this.voice = voice;
            return this;
        }

        public Builder setResponseFormat(String responseFormat) {
            this.responseFormat = responseFormat;
            return this;
        }

        public Builder setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public SpeechInput build() {
            return new SpeechInput(model, input, voice, responseFormat, speed);
        }
    }
}
