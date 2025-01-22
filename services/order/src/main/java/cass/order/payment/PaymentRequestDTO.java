package cass.order.payment;

import cass.order.customer.DTO.CustomerResponseDTO;
import cass.order.order.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponseDTO customer) {
}
