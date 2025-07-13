package org.homework.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherDataSerializer {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String serializeToJson(WeatherData weatherData) {
        try {
            return mapper.writeValueAsString(weatherData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
