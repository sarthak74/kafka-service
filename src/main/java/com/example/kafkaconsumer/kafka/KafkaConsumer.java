package com.example.kafkaconsumer.kafka;

import com.example.kafkaconsumer.entity.Employee;
import com.example.kafkaconsumer.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
    @Autowired
    private EmployeeService service;

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics = "firstTopic", groupId = "kafkaConsumer")
    public void consume(String employee){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employee employeeObject = mapper.readValue(employee, Employee.class);
            log.info("[.] Kafka Service Got request: " + employeeObject);
            Employee updatedEmployee = service.updateEmployee(employeeObject);
            kafkaProducer.sendMessage(updatedEmployee);
        } catch (Exception e) {
            e.getStackTrace();
            log.info("exception: " + e);

        }
    }
}
