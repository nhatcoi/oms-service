package com.nhatdev.ecommerce.order;

import com.nhatdev.ecommerce.customer.CustomerClient;
import com.nhatdev.ecommerce.exception.BusinessException;
import com.nhatdev.ecommerce.kafka.OrderConfirmation;
import com.nhatdev.ecommerce.kafka.OrderProducer;
import com.nhatdev.ecommerce.orderline.OrderLineRequest;
import com.nhatdev.ecommerce.orderline.OrderLineService;
import com.nhatdev.ecommerce.payment.PaymentClient;
import com.nhatdev.ecommerce.payment.PaymentRequest;
import com.nhatdev.ecommerce.product.ProductClient;
import com.nhatdev.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order: Customer not found with id " + request.customerId()));

        // purchase the products --> product service
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        var order = repository.save(mapper.toOrder(request));

        // persist the order
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        // send the order confirmation --> notification service -- kafka
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }


    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Order with id %s not found", orderId)));
    }
}
