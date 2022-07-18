package com.example.kafkaconsumer.repository;


import com.example.kafkaconsumer.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("from Employee where isDeleted=false")
    List<Employee> findAllEmployees();
}