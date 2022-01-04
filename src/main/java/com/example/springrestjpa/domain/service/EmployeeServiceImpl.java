package com.example.springrestjpa.domain.service;

import com.example.springrestjpa.domain.Employee;
import com.example.springrestjpa.domain.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    //TODO: ajustar removendo a logica do controller
    @Override
    public Employee editEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
