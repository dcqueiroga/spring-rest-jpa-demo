package com.example.springrestjpa.domain;

import java.util.List;

public interface EmployeeService {
    List<Employee> retrieveEmployees();

    Employee getEmployee(Long employeeId);

    void createEmployee(Employee employee);

    void editEmployee(Employee employee);

    void deleteEmployee(Long employeeId);
}
