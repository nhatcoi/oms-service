package com.nhatdev.ecommerce.order;

import com.nhatdev.ecommerce.customer.CustomerClient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    private final CustomerClient customerClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer

        // purchase the products --> product service

        // persist the order

        // start payment process

        // send the order confirmation --> notification service -- kafka


    }
}
