package com.nhatdev.ecommerce.order;

import com.nhatdev.ecommerce.customer.CustomerClient;
import com.nhatdev.ecommerce.exception.BusinessException;
import com.nhatdev.ecommerce.orderline.OrderLineRequest;
import com.nhatdev.ecommerce.orderline.OrderLineService;
import com.nhatdev.ecommerce.product.ProductClient;
import com.nhatdev.ecommerce.product.PurchaseRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;

    public Integer createOrder(@Valid OrderRequest request) {
        // check the customer
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order: Customer not found with id " + request.customerId()));

        // purchase the products --> product service
         this.productClient.purchaseProducts(request.products());

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

        // TODO: start payment process


        // send the order confirmation --> notification service -- kafka


    }
}
