package com.example.springrestjpa.application;

import com.example.springrestjpa.domain.Employee;
import com.example.springrestjpa.domain.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.retrieveEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    public void saveEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                               @RequestBody Employee employee) {
        Employee empl = employeeService.getEmployee(employeeId);
        if (empl != null) {
            employee.setId(empl.getId());
            employeeService.editEmployee(employee);
        }
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
