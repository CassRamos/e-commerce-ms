package cass.order.orderline;

import cass.order.order.entity.Order;
import cass.order.orderline.DTO.OrderLineRequestDTO;
import cass.order.orderline.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        return OrderLine.builder()
                .quantity(orderLineRequestDTO.quantity())
                .order(
                        Order.builder()
                                .id(orderLineRequestDTO.orderId())
                                .build()
                )
                .productId(orderLineRequestDTO.productId())
                .build();
    }
}
