package co.istad.oauth2springauthorizationserver2.controller;

import java.util.Set;

import co.istad.oauth2springauthorizationserver2.dto.ClientScopesRequest;
import co.istad.oauth2springauthorizationserver2.service.ClientManagementService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/clients")
public class ClientManagementController {

    private final ClientManagementService clientManagementService;

    public ClientManagementController(
            ClientManagementService clientManagementService) {
        this.clientManagementService = clientManagementService;
    }

    @PutMapping("/{clientId}/scopes")
    public ResponseEntity<Set<String>> updateScopes(
            @PathVariable String clientId,
            @Valid @RequestBody ClientScopesRequest request) {

        Set<String> updatedScopes = clientManagementService.updateScopes(
                clientId, request.scopes());

        return ResponseEntity.ok(updatedScopes);
    }
}