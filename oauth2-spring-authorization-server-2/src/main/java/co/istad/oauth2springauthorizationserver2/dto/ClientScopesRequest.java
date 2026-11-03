package co.istad.oauth2springauthorizationserver2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record ClientScopesRequest(
        @NotEmpty(message = "Scopes must not be empty")
        Set<@NotBlank(message = "Scope must not be blank") String> scopes
) {
}