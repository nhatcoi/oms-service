package com.nhatdev.ecommerce.dto;

import com.nhatdev.ecommerce.customer.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
