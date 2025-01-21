package cass.order.order;

import cass.order.customer.CustomerClient;
import cass.order.customer.DTO.CustomerResponseDTO;
import cass.order.exception.BusinessException;
import cass.order.kafka.OrderConfirmationDTO;
import cass.order.kafka.OrderProducer;
import cass.order.order.DTO.OrderRequestDTO;
import cass.order.order.DTO.OrderResponseDTO;
import cass.order.order.entity.Order;
import cass.order.orderline.DTO.OrderLineRequestDTO;
import cass.order.orderline.OrderLineService;
import cass.order.product.DTO.PurchaseRequestDTO;
import cass.order.product.DTO.PurchaseResponseDTO;
import cass.order.product.ProductClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    public List<OrderResponseDTO> findAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO findOrderById(Integer orderId) {
        return orderRepository
                .findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Cannot find order. No order found with id: %s ", orderId)));
    }

    public Integer createdOrder(OrderRequestDTO orderRequestDTO) {
        CustomerResponseDTO customer = customerClient.findCustomerById(orderRequestDTO.customerId())
                .orElseThrow(() -> new BusinessException(
                        String.format("Cannot create order. No customer found with id: %s ", orderRequestDTO.customerId())));


        List<PurchaseResponseDTO> purchasedProducts = this.productClient.purchaseProductsList(orderRequestDTO.products());
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

        //todo -> payment process

        orderProducer.sendOrderConfirmation(
                new OrderConfirmationDTO(
                        orderRequestDTO.reference(),
                        orderRequestDTO.amount(),
                        orderRequestDTO.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
    }
}
