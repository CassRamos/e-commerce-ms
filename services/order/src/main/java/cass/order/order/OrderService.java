package cass.order.order;

import cass.order.customer.CustomerClient;
import cass.order.customer.DTO.CustomerResponseDTO;
import cass.order.exception.BusinessException;
import cass.order.order.DTO.OrderRequestDTO;
import cass.order.order.entity.Order;
import cass.order.orderline.DTO.OrderLineRequestDTO;
import cass.order.orderline.OrderLineService;
import cass.order.product.DTO.PurchaseRequestDTO;
import cass.order.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderMapper orderMapper;

    public Integer createdOrder(OrderRequestDTO orderRequestDTO) {
        CustomerResponseDTO customer = customerClient.findCustomerById(orderRequestDTO.customerId())
                .orElseThrow(() -> new BusinessException(
                        String.format("Cannot create order. No customer found with id: %s ", orderRequestDTO.customerId())));

        this.productClient.purchaseProductsList(orderRequestDTO.products());

        Order order = this.orderRepository.save(orderMapper.toOrder(orderRequestDTO));

        for (PurchaseRequestDTO purchaseRequest : orderRequestDTO.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequestDTO(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        return null;
    }
}
