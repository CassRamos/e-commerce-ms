package cass.product.product;

import cass.product.product.DTO.ProductPurchaseRequestDTO;
import cass.product.product.DTO.ProductPurchaseResponseDTO;
import cass.product.product.DTO.ProductRequestDTO;
import cass.product.product.DTO.ProductResponseDTO;
import cass.product.product.entity.Product;
import cass.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponseDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponseDTO findProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product not found with ID: %s", productId)));
    }

    public Integer CreateProduct(ProductRequestDTO productRequestDTO) {
        var product = productMapper.toProduct(productRequestDTO);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponseDTO> purchaseProducts(List<ProductPurchaseRequestDTO> purchaseRequestDTOList) {
        List<Integer> productIds = purchaseRequestDTOList
                .stream()
                .map(ProductPurchaseRequestDTO::productId)
                .toList();

        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<ProductPurchaseRequestDTO> storedRequest = purchaseRequestDTOList
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequestDTO::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponseDTO>();

        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequestDTO productRequest = storedRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException(
                        String.format("Insufficient stock quantity for product with ID: %s ", product.getId()));
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper
                    .toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }


}
