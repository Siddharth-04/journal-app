package com.sid.journal.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current {
        private int temperature;

        @JsonProperty("weather_descriptions")
        List<String> weatherDescription;

        @JsonProperty("feelslike")
        private int feelsLike;
    }
}
