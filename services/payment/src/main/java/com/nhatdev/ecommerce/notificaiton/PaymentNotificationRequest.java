package com.nhatdev.ecommerce.notificaiton;

import com.nhatdev.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerLastName,
        String customerFirstName,
        String customerEmail
) {
}
