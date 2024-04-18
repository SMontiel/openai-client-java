package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;

public class ModelsSample {

    public static void list() {
        var client = new OpenAI(DotEnv.get("OPENAI_API_KEY"));
        client.models().list().data
                .stream() // sort by creation date in descending order
                .sorted((a, b) -> Integer.compare(b.created, a.created))
                .map(model -> model.id)
                .forEach(System.out::println);
    }
}
