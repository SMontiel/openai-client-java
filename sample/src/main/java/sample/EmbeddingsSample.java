package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.input.EmbeddingsInput;

public class EmbeddingsSample {
    public static OpenAI getClient() {
        return new OpenAI(DotEnv.get("OPENAI_API_KEY"));
    }

    public static void create() {
        var input = new EmbeddingsInput.Builder()
                .setInput("The food was delicious and the waiter...")
                .setModel("text-embedding-ada-002")
                .setEncodingFormat("float")
                .build();
        var embeddings = getClient().embeddings().create(input);
        System.out.println(embeddings);
    }
}
