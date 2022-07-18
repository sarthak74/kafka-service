package com.example.kafkaconsumer.service;

import com.example.kafkaconsumer.entity.Employee;
import com.example.kafkaconsumer.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repo;

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public Employee updateEmployee(Employee employeeUpdatedData){
        log.info("updating emp id: " + employeeUpdatedData.getId());
        String id = employeeUpdatedData.getId();
        Employee employee_to_be_updated = repo.findById(id).get();

        if(employee_to_be_updated == null) return null;
        if(employee_to_be_updated.isDeleted() == true) return null;

        employee_to_be_updated = setEmployeeFields(employee_to_be_updated, employeeUpdatedData);
        repo.save(employee_to_be_updated);
        return employee_to_be_updated;
    }

    public Employee setEmployeeFields(Employee employeeToBeUpdated, Employee newEmployee){
        if(newEmployee.getName() != null) employeeToBeUpdated.setName(newEmployee.getName());
        if(newEmployee.getPod() != null) employeeToBeUpdated.setPod(newEmployee.getPod());
        if(newEmployee.getContact() != null) employeeToBeUpdated.setContact(newEmployee.getContact());
        if(newEmployee.getAge() != null) employeeToBeUpdated.setAge(newEmployee.getAge());
        return employeeToBeUpdated;
    }
}