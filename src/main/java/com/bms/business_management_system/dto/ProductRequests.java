package com.bms.business_management_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public class ProductRequests {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required , please add it")
    @Positive(message = "Price should be positive")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be negative")
    @PositiveOrZero(message = "only positive or zero quantity is accepted")
    private Integer quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
