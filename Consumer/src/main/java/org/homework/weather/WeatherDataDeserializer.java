package org.homework.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherDataDeserializer implements Deserializer {
    private ObjectMapper objectMapper = new ObjectMapper();

    public WeatherDataDeserializer() {}
    public WeatherData deserializeWeatherData(String json) {
        WeatherData weatherData = null;
        try {
            weatherData = objectMapper.readValue(json, WeatherData.class);
        } catch (JsonProcessingException e) {
            System.out.println("Failed to deserialize WeatherData");
        }
        return weatherData;
    }
}
