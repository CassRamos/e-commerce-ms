package cass.product.product;

import cass.product.product.DTO.ProductPurchaseResponseDTO;
import cass.product.product.DTO.ProductRequestDTO;
import cass.product.product.DTO.ProductResponseDTO;
import cass.product.product.entity.Category;
import cass.product.product.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequestDTO requestDTO) {
        return Product.builder()
                .name(requestDTO.name())
                .description(requestDTO.description())
                .availableQuantity(requestDTO.availableQuantity())
                .price(requestDTO.price())
                .category(
                        Category.builder()
                                .id(requestDTO.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponseDTO toProductResponse(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponseDTO toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponseDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
