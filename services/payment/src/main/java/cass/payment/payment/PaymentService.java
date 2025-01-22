package cass.payment.payment;

import cass.payment.payment.DTO.PaymentRequestDTO;
import cass.payment.payment.entity.Payment;
import cass.payment.payment.notification.NotificationProducer;
import cass.payment.payment.notification.PaymentNotificationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer CreatePayment(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = paymentMapper.toPayment(paymentRequestDTO);

        notificationProducer.sendNotification(
                new PaymentNotificationRequestDTO(
                        paymentRequestDTO.orderReference(),
                        paymentRequestDTO.amount(),
                        paymentRequestDTO.paymentMethod(),
                        paymentRequestDTO.customer().firstname(),
                        paymentRequestDTO.customer().lastname(),
                        paymentRequestDTO.customer().email()
                )
        );

        return payment.getId();
    }
}
