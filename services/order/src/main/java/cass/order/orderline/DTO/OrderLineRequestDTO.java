package cass.order.orderline.DTO;

public record OrderLineRequestDTO(
        Integer orderId,
        Integer productId,
        double quantity
) {
}
