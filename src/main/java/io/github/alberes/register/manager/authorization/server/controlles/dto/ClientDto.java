package io.github.alberes.register.manager.authorization.server.controlles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Client")
public record ClientDto(
        @NotBlank(message = "Obligatory field")
        @Size(min = 10, max = 100, message = "Fill this field with size between 10 and 100")
        String clientId,
        @NotBlank(message = "Obligatory field")
        @Size(min = 10, max = 100, message = "Fill this field with size between 10 and 100")
        String clientSecret,
        @NotBlank(message = "Obligatory field")
        @Size(min = 10, max = 255, message = "Fill this field with size between 10 and 255")
        String redirectURI,
        @NotBlank(message = "Obligatory field")
        @Size(min = 4, max = 100, message = "Fill this field with size between 4 and 100")
        String scope) {
}