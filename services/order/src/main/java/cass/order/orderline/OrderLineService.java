package cass.order.orderline;

import cass.order.orderline.DTO.OrderLineRequestDTO;
import cass.order.orderline.entity.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        OrderLine order = orderLineMapper.toOrderLine(orderLineRequestDTO);
        return orderLineRepository.save(order).getId();
    }
}
