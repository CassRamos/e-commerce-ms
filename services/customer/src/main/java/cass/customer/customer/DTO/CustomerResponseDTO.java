package cass.customer.customer.DTO;

import cass.customer.customer.entity.Address;

public record CustomerResponseDTO(
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
