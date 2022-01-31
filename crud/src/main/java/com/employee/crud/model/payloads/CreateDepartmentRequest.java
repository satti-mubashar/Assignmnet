package com.employee.crud.model.payloads;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateDepartmentRequest {

    @NotNull( message =  "Last name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
