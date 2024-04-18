package sample;

import com.salvadormontiel.dotenv.DotEnv;
import com.salvadormontiel.openai.OpenAI;
import com.salvadormontiel.openai.response.Model;

public class ModelsSample {

    public static void list() {
        var client = new OpenAI(DotEnv.get("OPENAI_API_KEY"));
        client.models().list().data
                .stream() // sort by creation date in descending order
                .sorted((a, b) -> Integer.compare(b.created, a.created))
                .map(model -> model.id)
                .forEach(System.out::println);
    }

    public static void retrieve() {
        var client = new OpenAI(DotEnv.get("OPENAI_API_KEY"));
        Model model = client.models().retrieve("gpt-4-turbo-2024-04-09");
        System.out.println(model);
    }
}
