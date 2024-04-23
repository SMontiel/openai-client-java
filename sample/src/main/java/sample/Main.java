package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.input.SpeechInput;

public class Main {

    public static void main(String[] args) {
        //ChatCompletionSample.sync();

        //ChatCompletionSample.asyncStream();

        //ModelsSample.list();

        //ModelsSample.retrieve();

        //ModelsSample.delete();

        var client = new OpenAI(DotEnv.get("OPENAI_API_KEY"));

        var input = new SpeechInput.Builder()
                .setModel("tts-1")
                .setInput("The quick brown fox jumped over the lazy dog.")
                .setVoice("alloy")
                .build();
        var file = client.audio().speech().create(input);
        System.out.println(file.toAbsolutePath());
    }
}
