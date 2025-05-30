package com.nhatdev.ecommerce.customer.service;

import com.nhatdev.ecommerce.customer.Customer;
import com.nhatdev.ecommerce.dto.CustomerRequest;
import com.nhatdev.ecommerce.dto.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    String createCustomer(CustomerRequest request);

    Customer getCustomerById(String id);

    void updateCustomer(CustomerRequest request);

    void delete(String id);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String customerId);

    CustomerResponse findById(String customerId);
}