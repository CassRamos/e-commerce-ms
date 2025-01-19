package cass.product.product;

import cass.product.product.DTO.ProductPurchaseRequestDTO;
import cass.product.product.DTO.ProductPurchaseResponseDTO;
import cass.product.product.DTO.ProductRequestDTO;
import cass.product.product.DTO.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponseDTO> findProductById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequestDTO productRequest
    ) {
        return ResponseEntity.ok(productService.CreateProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponseDTO>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequestDTO> productPurchaseRequest
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(productPurchaseRequest));
    }


}
