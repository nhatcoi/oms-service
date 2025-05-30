package com.nhatdev.ecommerce.payment;

import com.nhatdev.ecommerce.customer.CustomerResponse;
import com.nhatdev.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
