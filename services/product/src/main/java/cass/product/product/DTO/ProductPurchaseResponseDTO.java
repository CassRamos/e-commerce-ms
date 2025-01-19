package cass.product.product.DTO;

import java.math.BigDecimal;

public record ProductPurchaseResponseDTO(
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
