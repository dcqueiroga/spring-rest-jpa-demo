package com.example.springrestjpa.domain.service;

import com.example.springrestjpa.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> retrieveEmployees();

    Employee getEmployee(Long employeeId);

    Employee createEmployee(Employee employee);

    Employee editEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
}
