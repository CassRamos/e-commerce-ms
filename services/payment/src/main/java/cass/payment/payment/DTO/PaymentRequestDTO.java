package cass.payment.payment.DTO;

import cass.payment.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
