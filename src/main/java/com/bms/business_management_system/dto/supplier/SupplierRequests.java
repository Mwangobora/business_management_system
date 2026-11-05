package com.bms.business_management_system.dto.supplier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class SupplierRequests {
    @NotBlank(message = "supplier name is required")
    private String name;

    @NotBlank(message = "phone number is required")
    @Positive(message = "please enter valid number")
    private String phoneNumber;

    @NotBlank(message = "email required")
    private String email;

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
