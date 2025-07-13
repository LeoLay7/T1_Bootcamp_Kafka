package org.homework.weather;

public class WeatherData {
    private String city;
    private int temperature;
    private String weatherType;

    public WeatherData() {}

    public WeatherData(String city, int temperature, String weatherType) {
        this.city = city;
        this.temperature = temperature;
        this.weatherType = weatherType;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public String getWeatherType() {
        return weatherType;
    }
    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }
}
