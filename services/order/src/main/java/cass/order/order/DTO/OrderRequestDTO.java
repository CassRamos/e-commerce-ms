package cass.order.order.DTO;

import cass.order.order.entity.PaymentMethod;
import cass.order.product.DTO.PurchaseRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(
        String reference,
        @Positive(message = "Order amount should be greater than 0")
        BigDecimal amount,
        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer ID is required")
        @NotEmpty(message = "Customer ID is required")
        @NotBlank(message = "Customer ID is required")
        String customerId,

        @NotEmpty(message = "You should at least purchase one product")
        List<PurchaseRequestDTO> products
) {
}
