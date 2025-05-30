package com.nhatdev.ecommerce.dto;

import com.nhatdev.ecommerce.customer.Address;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @NotNull(message = "Email is required")
        String email,
        Address address
) {
}
