package sample;

import com.salvadormontiel.openai.input.ImageEditInput;
import com.salvadormontiel.openai.input.ImageInput;
import com.salvadormontiel.openai.input.ImageVariationInput;

import java.nio.file.Paths;

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

    public static void edit() {
        var client = AudioSample.getClient();

        var image = Paths.get("sample-files/indoor-image.png");
        var mask = Paths.get("sample-files/indoor-mask.png");

        var input = new ImageEditInput.Builder()
                .setImage(image)
                .setMask(mask)
                .setModel("dall-e-2")
                .setPrompt("A sunlit indoor lounge area with a pool containing a car")
                .setN(1)
                .setSize("1024x1024")
                .build();
        var response = client.images().edit(input);
        System.out.println(response);
    }

    public static void createVariation() {
        var client = AudioSample.getClient();

        var image = Paths.get("sample-files/indoor-image.png");

        var input = new ImageVariationInput.Builder()
                .setImage(image)
                .setModel("dall-e-2")
                .setN(1)
                .setSize("1024x1024")
                .build();
        var response = client.images().createVariation(input);
        System.out.println(response);
    }
}
