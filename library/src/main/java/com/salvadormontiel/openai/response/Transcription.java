package com.salvadormontiel.openai.response;

import com.squareup.moshi.Json;

import java.util.List;

public class Transcription {
    public String text;
    public String language;
    public String duration;
    public List<Word> words;
    public List<Segment> segments;

    public Transcription(String text, String language, String duration, List<Word> words, List<Segment> segments) {
        this.text = text;
        this.language = language;
        this.duration = duration;
        this.words = words;
        this.segments = segments;
    }

    @Override
    public String toString() {
        return "Transcription{" +
                "text='" + text + '\'' +
                ", language='" + language + '\'' +
                ", duration='" + duration + '\'' +
                ", words=" + words +
                ", segments=" + segments +
                '}';
    }

    public static class Word {
        public String word;
        public double start;
        public double end;

        public Word(String word, double start, double end) {
            this.word = word;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Word{" +
                    "word='" + word + '\'' +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    public static class Segment {
        public int id;
        public int seek;
        public double start;
        public double end;
        public String text;
        public List<Integer> tokens;
        public double temperature;
        @Json(name = "system_fingerprint") public double avgLogProb;
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
