package co.istad.oauth2springauthorizationserver2.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/scopes")
public class ScopeController {

    private static final Set<String> AVAILABLE_SCOPES = Set.of(
            "openid",
            "profile",
            "client.read",
            "client.create"
    );

    @GetMapping
    public Set<String> getAllScopes() {
        return AVAILABLE_SCOPES;
    }
}