package org.homework;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.homework.kafka.KafkaProducerFactory;
import org.homework.kafka.WeatherProducer;
import org.homework.weather.WeatherGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        String SERVERS = System.getenv().getOrDefault("KAFKA_SERVERS", "localhost:9092");
        String TOPIC = System.getenv().getOrDefault("KAFKA_TOPIC", "weather-report");
        int sendMessageInterval = 2000;

        WeatherGenerator generator = new WeatherGenerator();
        TaskScheduler scheduler = new TaskScheduler();

        KafkaProducer<String, String> kafkaProducer = KafkaProducerFactory.getProducer(SERVERS);
        WeatherProducer producer = new WeatherProducer(generator);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Closing Kafka Producer...");
            kafkaProducer.flush();
            kafkaProducer.close();
        }));
        scheduler.schedule(() -> producer.sendMessage(kafkaProducer, TOPIC), sendMessageInterval);
    }
}