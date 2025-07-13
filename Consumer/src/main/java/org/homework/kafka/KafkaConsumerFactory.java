package org.homework.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class KafkaConsumerFactory {
    public static Properties getProperties(String servers) {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }

    public static KafkaConsumer<String, String> getKafkaConsumer(String servers) {
        Properties properties = getProperties(servers);
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        return kafkaConsumer;
    }

    public static void assignPartitions(KafkaConsumer<String, String> kafkaConsumer, String topic, List<Integer> partitions) {
        kafkaConsumer.assign(
                partitions.stream().map(p -> new TopicPartition(topic, p)).collect(Collectors.toList())
        );
    }
}
