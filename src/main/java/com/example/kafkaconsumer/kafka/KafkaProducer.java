package com.example.kafkaconsumer.kafka;

import com.example.kafkaconsumer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public String getMessage(Employee employee){
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
            return json;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(Employee employee){
        try {
            String message = getMessage(employee);
            log.info(String.format("[x] Sending Kafka request. Msg: " + message));
            kafkaTemplate.send("secondTopic", message);
        } catch (Exception e){
            e.printStackTrace();

        }
    }
}
