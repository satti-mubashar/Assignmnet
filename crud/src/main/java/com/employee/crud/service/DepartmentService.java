package com.employee.crud.service;

import com.employee.crud.Constants;
import com.employee.crud.exception.ResourceNotFoundException;
import com.employee.crud.model.entity.Department;
import com.employee.crud.model.payloads.CreateDepartmentRequest;
import com.employee.crud.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public Page<Department> departments(Pageable paging) {
        return departmentRepository.findAllByStatus(Constants.ACTIVE,paging);
    }

    public Optional<Department> addDepartment(CreateDepartmentRequest createDepartmentRequest) {
        Department department = new Department();
        department.setDepartmentName(createDepartmentRequest.getName());
        department.setStatus(Constants.ACTIVE);
        return Optional.ofNullable(departmentRepository.save(department));
    }

    public void deleteDepartment(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isPresent()) {
            department.get().setStatus(Constants.INACTIVE);
            departmentRepository.save(department.get());
        } else
            throw new ResourceNotFoundException("Department not found for this id :: " + departmentId);
    }

    public void updateDepartment() {

    }

}
