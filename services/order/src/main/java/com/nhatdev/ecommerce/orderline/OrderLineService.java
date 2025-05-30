package com.nhatdev.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository repository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        List<OrderLine> orderLines = repository.findAllByOrderId(orderId);
        if (orderLines.isEmpty()) {
            return List.of();
        }
        return orderLines.stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
