package com.nhatdev.ecommerce.kafka;

import com.nhatdev.ecommerce.customer.CustomerResponse;
import com.nhatdev.ecommerce.order.PaymentMethod;
import com.nhatdev.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> purchases
) {
}
