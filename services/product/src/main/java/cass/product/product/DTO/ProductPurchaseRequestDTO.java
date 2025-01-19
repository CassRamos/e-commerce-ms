package cass.product.product.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequestDTO(
        @NotNull(message = "Product id is required")
        Integer productId,
        @Positive(message = "Product quantity must be greater than 0")
        double quantity
) {
}
