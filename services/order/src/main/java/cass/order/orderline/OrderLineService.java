package cass.order.orderline;

import cass.order.orderline.DTO.OrderLineRequestDTO;
import cass.order.orderline.DTO.OrderLineResponseDTO;
import cass.order.orderline.entity.OrderLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public List<OrderLineResponseDTO> findAllByOrderId(Integer orderId) {
        return orderLineRepository
                .findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponseDTO)
                .collect(Collectors.toList());
    }

    public Integer saveOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        OrderLine order = orderLineMapper.toOrderLine(orderLineRequestDTO);
        return orderLineRepository.save(order).getId();
    }
}
