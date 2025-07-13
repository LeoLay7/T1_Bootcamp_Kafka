package org.homework;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.homework.analysis.KafkaConsumerRecordHandler;
import org.homework.analysis.WeatherAnalysisThread;
import org.homework.analysis.WeatherReportRecordHandler;
import org.homework.kafka.Consumer;
import org.homework.kafka.KafkaConsumerFactory;
import org.homework.weather.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        String SERVERS = System.getenv().getOrDefault("KAFKA_SERVERS", "localhost:9092");
        String TOPIC = System.getenv().getOrDefault("KAFKA_TOPIC", "weather-report");
        String partitionsEnv = System.getenv().getOrDefault("TOPIC_TOPIC_PARTITIONS", "0");
        List<Integer> TOPIC_PARTITIONS = Arrays.stream(partitionsEnv.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();

        int pollMillis = 2000;
        int showAnalyzeInfoIntervalMillis = 10000;

        WeatherCitiesData data = new WeatherCitiesData();
        WeatherDataDeserializer weatherDataDeserializer = new WeatherDataDeserializer();
        WeatherDataAnalyzer weatherDataAnalyzer = new WeatherDataAnalyzer();
        WeatherDataPrinter weatherDataPrinter = new WeatherDataPrinter();

        KafkaConsumerRecordHandler handler = new WeatherReportRecordHandler(data, weatherDataDeserializer);

        KafkaConsumer<String, String> kafkaConsumer = KafkaConsumerFactory.getKafkaConsumer(
                SERVERS
        );
        KafkaConsumerFactory.assignPartitions(kafkaConsumer, TOPIC, TOPIC_PARTITIONS);

        WeatherAnalysisThread weatherAnalysisThread = new WeatherAnalysisThread(
                data,
                weatherDataAnalyzer,
                weatherDataPrinter,
                showAnalyzeInfoIntervalMillis
        );

        AtomicBoolean running = new AtomicBoolean(true);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            weatherAnalysisThread.stopRunning();
            running.set(false);
        }));

        weatherAnalysisThread.setDaemon(true);
        weatherAnalysisThread.start();

        try {
            Consumer consumer = new Consumer(kafkaConsumer, handler);
            while (running.get()) {
                consumer.consume(pollMillis);
            }
        } finally {
            kafkaConsumer.close();
        }

    }
}