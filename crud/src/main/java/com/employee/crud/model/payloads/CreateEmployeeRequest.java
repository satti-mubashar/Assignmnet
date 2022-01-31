package com.employee.crud.model.payloads;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class CreateEmployeeRequest {

    @NotNull( message =  "First name cannot be null")
    @Size(min = 2, message = "First name is too short, minimum 2 character require")
    String firstName;

    @NotNull( message =  "Last name cannot be null")
    @Size(min = 2, message = "Last name is too short, minimum 2 character require")
    String lastName;

    @NotNull( message =  "Email cannot be null")
    @NotEmpty(message =  "Email cannot be null")
    @Email(message = "Email is not valid")
    String email;

    @Min(value = 1, message = "Salary should be greater than 0")
    private Double salary;

    @Valid
    private ManagerRequest manager;

    @Valid
    private EmployeeDepartmentRequest department;

    @Pattern(regexp = "^[0-9-]*", message ="Phone number is not valid" )
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public ManagerRequest getManager() {
        return manager;
    }

    public void setManager(ManagerRequest manager) {
        this.manager = manager;
    }

    public EmployeeDepartmentRequest getDepartment() {
        return department;
    }

    public void setDepartment(EmployeeDepartmentRequest department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
