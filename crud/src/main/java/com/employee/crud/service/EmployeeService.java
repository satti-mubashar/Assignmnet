package com.employee.crud.service;

import com.employee.crud.Constants;
import com.employee.crud.exception.ResourceNotFoundException;
import com.employee.crud.model.entity.Department;
import com.employee.crud.model.entity.Employee;
import com.employee.crud.model.payloads.CreateEmployeeRequest;
import com.employee.crud.repository.DepartmentRepository;
import com.employee.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public Page<Employee> employees(Pageable pageable) {
        return employeeRepository.findAllByStatus(Constants.ACTIVE,pageable);
    }

    public List<Employee> employeess( ) {

        return employeeRepository.findAll();
    }

    public Optional<Employee> addEmployee(CreateEmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setEmail(employeeRequest.getEmail());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setStatus(Constants.ACTIVE);

        if (Objects.nonNull(employeeRequest.getManager())) {
            Employee employeeManger = new Employee();
            employeeManger.setId(employeeRequest.getManager().getId());
            employee.setManager(employeeManger);
        }

        if (Objects.nonNull(employeeRequest.getDepartment())) {
            Department department = departmentRepository.getById(employeeRequest.getDepartment().getId());
            employee.setDepartment(department);
        }
        return Optional.ofNullable(employeeRepository.save(employee));
    }

    public Employee findEmployeeDetails(Long employeeId) {

        return employeeRepository.findById(employeeId).map(employees ->{
            return employees;
        }).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

    }

    public void deleteEmployee(Long employeeId) {

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            employee.get().setStatus(Constants.INACTIVE);
            employeeRepository.save(employee.get());
        } else
            throw new ResourceNotFoundException("Employee not found for this id :: " + employeeId);
    }

    public Optional<Employee> updateEmployee(Long employeeId, CreateEmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setSalary(employeeRequest.getSalary());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        if (Objects.nonNull(employeeRequest.getManager())) {
            Employee employeeManger = new Employee();
            employeeManger.setId(employeeRequest.getManager().getId());
            employee.setManager(employeeManger);
        }

        if (Objects.nonNull(employeeRequest.getDepartment())) {
            Department department = departmentRepository.getById(employeeRequest.getDepartment().getId());
            employee.setDepartment(department);
        }
        return Optional.ofNullable(employeeRepository.save(employee));
    }
}
