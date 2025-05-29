package com.nhatdev.ecommerce.customer.service;

import com.nhatdev.ecommerce.customer.Customer;
import com.nhatdev.ecommerce.dto.CustomerRequest;
import com.nhatdev.ecommerce.dto.CustomerResponse;
import com.nhatdev.ecommerce.customer.CustomerMapper;
import com.nhatdev.ecommerce.customer.CustomerRepository;
import com.nhatdev.ecommerce.handler.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public Customer getCustomerById(String id) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String .format("Customer with id %s not found", request.id()))
                );
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    @Override
    public void delete(String id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", id))
                );
        repository.delete(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isBlank(request.lastName())) {
            customer.setFirstName(request.lastName ());
        }
        if (StringUtils.isBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer).toList();
    }

    @Override
    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    @Override
    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId))
                );

    }
}
