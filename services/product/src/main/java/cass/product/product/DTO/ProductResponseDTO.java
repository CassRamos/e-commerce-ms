package cass.product.product.DTO;

import java.math.BigDecimal;

public record ProductResponseDTO(
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        String categoryName,
        String categoryDescription
) {
}
