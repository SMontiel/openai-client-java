package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.input.SpeechInput;
import com.salvadormontiel.openai.input.TranscriptionInput;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AudioSample {
    public static OpenAI getClient() {
        return new OpenAI(DotEnv.get("OPENAI_API_KEY"));
    }

    public static void createSpeech() {
        var input = new SpeechInput.Builder()
                .setModel("tts-1")
                .setInput("The quick brown fox jumped over the lazy dog.")
                .setVoice("alloy")
                .build();
        var file = getClient().audio().speech().create(input);
        System.out.println(file.toAbsolutePath());
    }

    public static void createSimpleTranscription() {
        Path localFile = Paths.get("sample-files/speech.mp3");
        var input = new TranscriptionInput.Builder()
                .setModel("whisper-1")
                .setFile(localFile)
                .build();
        var transcription = getClient().audio().transcriptions().create(input);
        System.out.println(transcription);
    }

    public static void createVerboseTranscription() {
        Path localFile = Paths.get("sample-files/speech.mp3");
        var input = new TranscriptionInput.Builder()
                .setModel("whisper-1")
                .setFile(localFile)
                .addTimestampGranularity("word")
                .addTimestampGranularity("segment")
                .setResponseFormat("verbose_json")
                .build();
        var transcription = getClient().audio().transcriptions().create(input);
        System.out.println(transcription);
    }
}
