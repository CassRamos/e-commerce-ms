package cass.order.product.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequestDTO(
        @NotNull(message = "Product ID is required")
        Integer productId,
        @Positive(message = "Quantity should be greater than 0")
        double quantity
) {
}
