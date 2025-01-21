package cass.order.kafka;

import cass.order.customer.DTO.CustomerResponseDTO;
import cass.order.order.entity.PaymentMethod;
import cass.order.product.DTO.PurchaseResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationDTO(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponseDTO customer,
        List<PurchaseResponseDTO> products
) {
}
