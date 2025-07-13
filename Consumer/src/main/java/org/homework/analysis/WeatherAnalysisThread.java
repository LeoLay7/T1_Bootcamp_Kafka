package org.homework.analysis;

import org.homework.weather.WeatherCitiesData;
import org.homework.weather.WeatherDataAnalyzer;
import org.homework.weather.WeatherDataPrinter;

import java.util.Map;

public class WeatherAnalysisThread extends Thread {
    private final WeatherCitiesData weatherData;
    private final WeatherDataAnalyzer analyzer;
    private final WeatherDataPrinter printer;
    private final int sleepTimeMillis;
    private volatile boolean running = true;

    public WeatherAnalysisThread(WeatherCitiesData weatherData, WeatherDataAnalyzer analyzer, WeatherDataPrinter printer, int sleepTimeMillis) {
        this.weatherData = weatherData;
        this.analyzer = analyzer;
        this.printer = printer;
        this.sleepTimeMillis = sleepTimeMillis;
    }

    public void stopRunning() {
        this.running = false;
    }

    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(sleepTimeMillis);
                Map<String, Integer> maxCitiesTemp = analyzer.findMaxTempByCity(weatherData);
                Map<String, Double> avgCitiesTemp = analyzer.findAvgTempByCity(weatherData);

                if (!maxCitiesTemp.isEmpty()) printer.mapDataPrinter(
                        maxCitiesTemp,
                        "City: %s. Max temperature: %d",
                        "========================================\nMax temperature for all cities:",
                        "========================================"
                );
                if (!avgCitiesTemp.isEmpty()) printer.mapDataPrinter(
                        avgCitiesTemp,
                        "City: %s. Average temperature: %f",
                        "========================================\nAverage temperature for all cities:",
                        "========================================"
                );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }


}
