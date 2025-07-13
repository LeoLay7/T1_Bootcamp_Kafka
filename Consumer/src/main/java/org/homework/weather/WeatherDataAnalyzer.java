package org.homework.weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherDataAnalyzer {

    public WeatherData findMaxCityTemp(WeatherCitiesData weatherCitiesData, String city) {
        List<WeatherData> weatherData = weatherCitiesData.getWeatherDataByCity(city);
        if (weatherData == null || weatherData.isEmpty()) {
            return null;
        }
        WeatherData dataWithMaxTemp = weatherData.get(0);
        for (int i = 1; i < weatherData.size(); i++) {
            WeatherData data = weatherData.get(i);
            if (data.getTemperature() > dataWithMaxTemp.getTemperature()) {
                dataWithMaxTemp = data;
            }
        }
        return dataWithMaxTemp;
    }

    public Double findAverageCityTemp(WeatherCitiesData weatherCitiesData, String city) {
        List<WeatherData> weatherData = weatherCitiesData.getWeatherDataByCity(city);
        if (weatherData == null || weatherData.isEmpty()) {
            return null;
        }
        int summaryTemp = 0;
        for (int i = 0; i < weatherData.size(); i++) {
            summaryTemp += weatherData.get(i).getTemperature();
        }
        return (double) summaryTemp / weatherData.size();
    }

    public Map<String, Integer> findMaxTempByCity(WeatherCitiesData weatherCitiesData) {
        Map<String, Integer> maxTempByCity = new HashMap<>();
        for (String city: weatherCitiesData.getWeatherDataByCity().keySet()) {
            WeatherData maxTempData = findMaxCityTemp(weatherCitiesData, city);
            if (maxTempData != null) {
                maxTempByCity.put(city, maxTempData.getTemperature());
            }
        }
        return maxTempByCity;
    }

    public Map<String, Double> findAvgTempByCity(WeatherCitiesData weatherCitiesData) {
        Map<String, Double> avgTempByCity = new HashMap<>();
        for (String city: weatherCitiesData.getWeatherDataByCity().keySet()) {
            Double avgTempData = findAverageCityTemp(weatherCitiesData, city);
            if (avgTempData != null) {
                avgTempByCity.put(city, avgTempData);
            }
        }
        return avgTempByCity;
    }
}
