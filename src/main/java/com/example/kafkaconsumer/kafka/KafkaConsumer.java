package com.example.kafkaconsumer.kafka;

import com.example.kafkaconsumer.entity.Employee;
import com.example.kafkaconsumer.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private EmployeeService service;

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics = "firstTopic", groupId = "kafkaConsumer")
    public void consume(String employee){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Employee employeeObject = mapper.readValue(employee, Employee.class);
            System.out.println("[.] Kafka Service Got request: " + employeeObject);
            Employee updatedEmployee = service.updateEmployee(employeeObject);
            kafkaProducer.sendMessage(updatedEmployee);
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println("exception: " + e);

        }
    }
}
