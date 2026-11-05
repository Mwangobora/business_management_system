package com.bms.business_management_system.dto.categories;

import jakarta.validation.constraints.NotNull;

public class CategoryRequests {

    @NotNull(message = "Name is required")
    private String name;

    private  String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
