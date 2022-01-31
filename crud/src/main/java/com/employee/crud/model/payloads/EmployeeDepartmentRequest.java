package com.employee.crud.model.payloads;

import javax.validation.constraints.NotNull;

public class EmployeeDepartmentRequest {

    @NotNull( message =  "Department Id cannot be null")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
