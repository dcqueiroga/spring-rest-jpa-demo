package com.example.springrestjpa.application.rest;

import com.example.springrestjpa.application.dto.converter.EmployeeConverter;
import com.example.springrestjpa.application.dto.EmployeeDTO;
import com.example.springrestjpa.domain.Employee;
import com.example.springrestjpa.domain.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1/employees"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private static final String NEW_EMPLOYEE_LOG = "New employee was created: {}";
    private static final String EMPLOYEE_DELETED_LOG = "Employee id {} was deleted.";
    private static final String EMPLOYEE_UPDATED_LOG = "Employee id {} was updated.";

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeConverter employeeConverter;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<Employee> results = employeeService.retrieveEmployees();
        return ResponseEntity.ok(results.parallelStream()
                .map(employeeConverter::convertToDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeConverter.convertToDTO(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee createdEmployee = employeeService.createEmployee(employeeConverter.convertToEntity(employeeDTO));
        log.info(NEW_EMPLOYEE_LOG, createdEmployee.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeConverter.convertToDTO(createdEmployee));
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(name = "employeeId") Long employeeId,
                                   @Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee empl = employeeService.getEmployee(employeeId);
        if (empl == null) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = employeeConverter.convertToEntity(employeeDTO);
        employee.setId(empl.getId());
        Employee updatedEmployee = employeeService.editEmployee(employee);

        log.info(EMPLOYEE_UPDATED_LOG, employeeId);
        return ResponseEntity.ok(employeeConverter.convertToDTO(updatedEmployee));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee empl = employeeService.getEmployee(employeeId);
        if (empl == null) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployee(employeeId);

        log.info(EMPLOYEE_DELETED_LOG, employeeId);
        return ResponseEntity.ok().build();
    }
}
