package org.homework.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class WeatherCitiesData {
    private final Map<String, List<WeatherData>> weatherDataByCity;

    public WeatherCitiesData() {
        weatherDataByCity = new ConcurrentHashMap<>();
    }

    public void addCityWeatherData(WeatherData cityWeatherData) {
        String city = cityWeatherData.getCity();
        weatherDataByCity.computeIfAbsent(city, k -> new ArrayList<>()).add(cityWeatherData);
    }

    public Map<String, List<WeatherData>> getWeatherDataByCity() {
        return weatherDataByCity;
    }

    public List<String> getCities() {
        return new ArrayList<>(weatherDataByCity.keySet());
    }

    public List<WeatherData> getWeatherDataByCity(String city) {
        if (weatherDataByCity.containsKey(city)) {
            return weatherDataByCity.get(city);
        }
        return null;
    }
}
