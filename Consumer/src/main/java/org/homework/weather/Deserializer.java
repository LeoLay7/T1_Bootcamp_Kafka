package org.homework.weather;

public interface Deserializer {
    WeatherData deserializeWeatherData(String json);
}
