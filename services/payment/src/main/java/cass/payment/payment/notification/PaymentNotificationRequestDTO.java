package cass.payment.payment.notification;

import cass.payment.payment.entity.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequestDTO(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
