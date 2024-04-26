package sample;

import com.salvadormontiel.openai.FunctionProperties;
import com.salvadormontiel.openai.PropertyDetails;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Weather implements FunctionProperties {
    @NotNull
    @PropertyDetails(description = "The city and state, e.g. San Francisco, CA", required = true)
    public String location;

    @Nullable
    public WeatherUnit unit;
}
