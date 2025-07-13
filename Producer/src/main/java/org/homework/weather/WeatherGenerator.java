package org.homework.weather;

import java.util.List;
import java.util.Random;

public class WeatherGenerator implements RandomDataGenerator<WeatherData>  {
    private final static List<String> cities = List.of(
            "Москва",
            "Волгоград",
            "Магадан",
            "Королёв",
            "Санкт-Петербург",
            "Мурманск",
            "Липецк",
            "Благовещенск"
    );
    private final static int minTemp = -10;
    private final static int maxTemp = 35;
    private final static List<String> weatherTypes = List.of(
            "Солнечно",
            "Облачно",
            "Безоблачно",
            "Дождь",
            "Пасмурно"
    );

    private static final Random random = new Random();

    public WeatherData generateData() {
        int randomTemperature = random.nextInt(maxTemp - minTemp) + minTemp;
        String randomCity = cities.get(random.nextInt(cities.size()));
        String randomWeather = weatherTypes.get(random.nextInt(weatherTypes.size()));
        return new WeatherData(randomCity, randomTemperature, randomWeather);
    }

}
