package com.example.springrestjpa.application.rest;

import com.example.springrestjpa.domain.Employee;
import com.example.springrestjpa.domain.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/employees"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private static final String NEW_EMPLOYEE_LOG = "New employee was created: {}";
    private static final String EMPLOYEE_DELETED_LOG = "Employee id {} was deleted.";
    private static final String EMPLOYEE_UPDATED_LOG = "Employee id {} was updated.";

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.retrieveEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        log.info(NEW_EMPLOYEE_LOG, createdEmployee.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                                   @RequestBody Employee employee) {
        Employee empl = employeeService.getEmployee(employeeId);
        if (empl == null) {
            ResponseEntity.notFound().build();
        }

        employee.setId(empl.getId());
        Employee updatedEmployee = employeeService.editEmployee(employee);

        log.info(EMPLOYEE_UPDATED_LOG, employeeId);

        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        log.info(EMPLOYEE_DELETED_LOG, employeeId);
        return ResponseEntity.ok().build();
    }
}
