package cass.customer.customer.DTO;

import cass.customer.customer.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
        String id,
        @NotNull(message = "Customer first name is required!")
        String firstName,
        @NotNull(message = "Customer last name is required!")
        String lastName,
        @NotNull(message = "Customer email is required!")
        @Email(message = "Customer email is invalid!")
        String email,
        Address address
) {
}
