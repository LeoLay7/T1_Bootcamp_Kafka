package org.homework.analysis;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.homework.weather.Deserializer;
import org.homework.weather.WeatherCitiesData;
import org.homework.weather.WeatherData;

public class WeatherReportRecordHandler implements KafkaConsumerRecordHandler {
    private WeatherCitiesData weatherCitiesData;
    private Deserializer recordDeserializer;

    public WeatherReportRecordHandler(WeatherCitiesData weatherCitiesData, Deserializer recordDeserializer) {
        this.weatherCitiesData = weatherCitiesData;
        this.recordDeserializer = recordDeserializer;
    }


    @Override
    public void handle(ConsumerRecords<String, String> records) {
        for (ConsumerRecord<String, String> record : records) {
            WeatherData data = recordDeserializer.deserializeWeatherData(record.value());
            if (data != null) {
                weatherCitiesData.addCityWeatherData(data);
                System.out.println(record.value());
            }
        }
    }
}
