package cass.order.product;

import cass.order.exception.BusinessException;
import cass.order.product.DTO.PurchaseRequestDTO;
import cass.order.product.DTO.PurchaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {

    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponseDTO> purchaseProductsList(List<PurchaseRequestDTO> purchaseRequestList) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequestDTO>> requestEntity = new HttpEntity<>(purchaseRequestList, httpHeaders);

        ParameterizedTypeReference<List<PurchaseResponseDTO>> responseType =
                new ParameterizedTypeReference<List<PurchaseResponseDTO>>() {
                };

        ResponseEntity<List<PurchaseResponseDTO>> responseEntity = restTemplate.exchange(
                productUrl + "/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );

        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException(
                    String.format("Error while purchasing products: %s", responseEntity.getStatusCode()));
        }
        return responseEntity.getBody();
    }
}
