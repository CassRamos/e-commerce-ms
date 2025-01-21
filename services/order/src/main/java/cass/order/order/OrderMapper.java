package cass.order.order;

import cass.order.order.DTO.OrderRequestDTO;
import cass.order.order.DTO.OrderResponseDTO;
import cass.order.order.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public Order toOrder(OrderRequestDTO orderRequestDTO) {
        if (orderRequestDTO == null) {
            return null;
        }
        return Order.builder()
                .reference(orderRequestDTO.reference())
                .totalAmount(orderRequestDTO.amount())
                .paymentMethod(orderRequestDTO.paymentMethod())
                .customerId(orderRequestDTO.customerId())
                .build();
    }

    public OrderResponseDTO fromOrder(Order order) {
        return new OrderResponseDTO(
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
