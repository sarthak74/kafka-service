package com.example.kafkaconsumer.constants;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaConstants {
    private String bootstrapAddress = "localhost:9092";
    private String consumerGroupId = "kafkaConsumer";
    private String updateRequestTopic = "firstTopic";

}
