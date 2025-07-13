package org.homework.weather;

public class WeatherData {
    private String city;
    private int temperature;
    private String weatherType;

    public WeatherData(String city, int temperature, String weatherType) {
        this.city = city;
        this.temperature = temperature;
        this.weatherType = weatherType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getCity() {
        return city;
    }
    public int getTemperature() {
        return temperature;
    }

    public String getWeatherType() {
        return weatherType;
    }
}
