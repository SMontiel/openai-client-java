package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

import java.util.List;

public class Translation {
    public String text;
    public String language;
    public String duration;
    public List<Segment> segments;
    public String task;

    public Translation(String text, String language, String duration, List<Segment> segments, String task) {
        this.text = text;
        this.language = language;
        this.duration = duration;
        this.segments = segments;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "text='" + text + '\'' +
                (language != null ? ", language='" + language + '\'' : "") +
                (duration != null ? ", duration='" + duration + '\'' : "") +
                (segments != null ? ", segments=" + segments : "") +
                (task != null ? ", task='" + task + '\'' : "") +
                '}';
    }

    public static class Segment {
        public int id;
        public int seek;
        public double start;
        public double end;
        public String text;
        public List<Integer> tokens;
        public double temperature;
        @Json(name = "avg_logprob") public double avgLogProb;
        @Json(name = "compression_ratio") public double compressionRatio;
        @Json(name = "no_speech_prob") public double noSpeechProb;

        public Segment(int id, int seek, double start, double end, String text, List<Integer> tokens, double temperature, double avgLogProb, double compressionRatio, double noSpeechProb) {
            this.id = id;
            this.seek = seek;
            this.start = start;
            this.end = end;
            this.text = text;
            this.tokens = tokens;
            this.temperature = temperature;
            this.avgLogProb = avgLogProb;
            this.compressionRatio = compressionRatio;
            this.noSpeechProb = noSpeechProb;
        }

        @Override
        public String toString() {
            return "Segment{" +
                    "id=" + id +
                    ", seek=" + seek +
                    ", start=" + start +
                    ", end=" + end +
                    ", text='" + text + '\'' +
                    ", tokens=" + tokens +
                    ", temperature=" + temperature +
                    ", avgLogProb=" + avgLogProb +
                    ", compressionRatio=" + compressionRatio +
                    ", noSpeechProb=" + noSpeechProb +
                    '}';
        }
    }
}
