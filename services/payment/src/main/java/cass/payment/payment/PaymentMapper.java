package cass.payment.payment;

import cass.payment.payment.DTO.PaymentRequestDTO;
import cass.payment.payment.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequestDTO paymentRequestDTO) {
        if (paymentRequestDTO == null) {
            return null;
        }

        return Payment.builder()
                .orderId(paymentRequestDTO.orderId())
                .paymentMethod(paymentRequestDTO.paymentMethod())
                .amount(paymentRequestDTO.amount())
                .build();
    }
}
