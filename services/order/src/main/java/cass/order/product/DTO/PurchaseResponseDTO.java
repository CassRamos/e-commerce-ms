package cass.order.product.DTO;

import java.math.BigDecimal;

public record PurchaseResponseDTO(
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
