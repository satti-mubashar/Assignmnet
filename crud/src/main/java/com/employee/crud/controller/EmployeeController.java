package com.employee.crud.controller;


import com.employee.crud.exception.ResourceCreationException;
import com.employee.crud.model.entity.Employee;
import com.employee.crud.model.payloads.CreateEmployeeRequest;
import com.employee.crud.model.response.ResponseWrapper;
import com.employee.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees( @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(
                new ResponseWrapper(true, employeeService.employees(paging),
                        HttpStatus.OK.value()));
    }

    @GetMapping("/employeess")
    public ResponseEntity<?> getAllEmployees( ) {
        return ResponseEntity.ok(
                new ResponseWrapper(true, employeeService.employeess(),
                        HttpStatus.OK.value()));
    }


    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeDetails(@PathVariable(value = "id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok(
                new ResponseWrapper(true, "Deleted",
                        HttpStatus.OK.value()));
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        return ResponseEntity.ok(
                new ResponseWrapper(true, employeeService.findEmployeeDetails(employeeId),
                        HttpStatus.OK.value()));
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){

        return employeeService.addEmployee(createEmployeeRequest).map(employee -> {
            return ResponseEntity.ok(
                    new ResponseWrapper(true,"Employee has been created successfully" ,HttpStatus.OK.value()));
        }).orElseThrow(() -> new ResourceCreationException("Failed to create employee  with name : "+
                createEmployeeRequest.getFirstName()));

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity <?> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                             @Valid @RequestBody CreateEmployeeRequest employeeRequest){
        return employeeService.updateEmployee(employeeId, employeeRequest).map(employee -> {
            return ResponseEntity.ok(
                    new ResponseWrapper(true,"Employee has been updated successfully" ,HttpStatus.OK.value()));
        }).orElseThrow(() -> new ResourceCreationException("Failed to update employee  with id : "+employeeId));
    }
}
