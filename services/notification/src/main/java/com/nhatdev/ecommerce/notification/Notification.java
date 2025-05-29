package com.nhatdev.ecommerce.notification;

import com.nhatdev.ecommerce.kafka.order.OrderConfirmation;
import com.nhatdev.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Builder
public class Notification {

    @Id
    private String id;

    private NotificationType type;

    private LocalDateTime timestamp;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;
}
