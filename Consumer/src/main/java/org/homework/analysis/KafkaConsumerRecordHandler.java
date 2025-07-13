package org.homework.analysis;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaConsumerRecordHandler {
    void handle (ConsumerRecords<String, String> records);
}
