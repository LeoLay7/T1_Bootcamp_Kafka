package org.homework.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.homework.weather.WeatherData;
import org.homework.weather.WeatherDataSerializer;
import org.homework.weather.WeatherGenerator;

public class WeatherProducer {
    private WeatherGenerator generator;

    public WeatherProducer(WeatherGenerator generator) {
        this.generator = generator;
    }

    public void sendMessage(KafkaProducer<String, String> producer, String topic) {
        WeatherData data = generator.generateData();
        String json = WeatherDataSerializer.serializeToJson(data);
        producer.send(new ProducerRecord<>(topic, json));
    }


}
