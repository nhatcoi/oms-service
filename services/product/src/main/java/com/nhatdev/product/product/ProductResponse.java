package com.nhatdev.product.product;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        double availableQuantity,
        String categoryId,
        String categoryName,
        String categoryDescription
) {
}
