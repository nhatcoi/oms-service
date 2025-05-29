package com.nhatdev.ecommerce.controller;

import com.nhatdev.ecommerce.dto.CustomerRequest;
import com.nhatdev.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(
            @RequestBody @Valid CustomerRequest request) {
        String createdCustomer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        customerService.updateCustomer(request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<?> existsById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") String customerId
    ) {
        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }
}
