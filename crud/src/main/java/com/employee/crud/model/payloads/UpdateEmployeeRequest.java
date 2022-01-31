package com.employee.crud.model.payloads;

import javax.validation.constraints.NotNull;

public class UpdateEmployeeRequest extends CreateEmployeeRequest{

    @NotNull( message =  "Employee Id cannot be null")
    private Long employeeId;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
