package org.homework.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.homework.analysis.KafkaConsumerRecordHandler;

import java.time.Duration;

public class Consumer {
    private final KafkaConsumer<String, String> consumer;
    private final KafkaConsumerRecordHandler recordHandler;

    public Consumer(KafkaConsumer<String, String> consumer, KafkaConsumerRecordHandler recordHandler) {
        this.consumer = consumer;
        this.recordHandler = recordHandler;
    }

    public void consume(int pollMillis) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(pollMillis));
        recordHandler.handle(records);
    }
}
