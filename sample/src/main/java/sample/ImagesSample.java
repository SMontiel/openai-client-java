package sample;

import com.salvadormontiel.openai.input.ImageInput;

public class ImagesSample {

    public static void generate() {
        var client = AudioSample.getClient();

        var input = new ImageInput.Builder()
                .setModel("dall-e-3")
                .setPrompt("A cute baby sea otter")
                .setN(1)
                .setSize("1024x1024")
                .build();
        var response = client.images().generate(input);
        System.out.println(response);
    }
}
