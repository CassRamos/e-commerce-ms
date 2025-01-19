package cass.product.product.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequestDTO(
        Integer id,
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @Positive(message = "Product available quantity must be greater than 0")
        double availableQuantity,
        @Positive(message = "Product price must be greater than 0")
        BigDecimal price,
        @NotNull(message = "Product category id is required")
        Integer categoryId
) {
}
