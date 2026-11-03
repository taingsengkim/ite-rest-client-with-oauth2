package co.istad.oauth2springauthorizationserver2.service;

import java.util.Set;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientManagementServiceImpl implements ClientManagementService {

    private final RegisteredClientRepository registeredClientRepository;

    public ClientManagementServiceImpl(
            RegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    public Set<String> updateScopes(String clientId, Set<String> scopes) {

        RegisteredClient client =
                registeredClientRepository.findByClientId(clientId);

        if (client == null) {
            throw new IllegalArgumentException(
                    "Client not found: " + clientId);
        }

        RegisteredClient updatedClient = RegisteredClient
                .from(client)
                .scopes(existingScopes -> {
                    existingScopes.clear();
                    existingScopes.addAll(scopes);
                })
                .build();

        registeredClientRepository.save(updatedClient);

        return updatedClient.getScopes();
    }
}