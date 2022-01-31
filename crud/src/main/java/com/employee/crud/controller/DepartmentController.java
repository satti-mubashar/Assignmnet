package com.employee.crud.controller;


import com.employee.crud.exception.ResourceCreationException;
import com.employee.crud.model.payloads.CreateDepartmentRequest;
import com.employee.crud.model.payloads.CreateEmployeeRequest;
import com.employee.crud.model.response.ResponseWrapper;
import com.employee.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/api/v1")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<?> getAllDepartments(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(
                new ResponseWrapper(true, departmentService.departments(paging),
                        HttpStatus.OK.value()));
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable(value = "id") Long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(
                new ResponseWrapper(true, "Deleted",
                        HttpStatus.OK.value()));
    }

    @PostMapping("/departments")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody CreateDepartmentRequest createDepartmentRequest) {

        return departmentService.addDepartment(createDepartmentRequest).map(department -> {
            return ResponseEntity.ok(
                    new ResponseWrapper(true, "Department has been created successfully", HttpStatus.OK.value()));
        }).orElseThrow(() -> new ResourceCreationException("Failed to create department  with name : " +
                createDepartmentRequest.getName()));

    }
}
