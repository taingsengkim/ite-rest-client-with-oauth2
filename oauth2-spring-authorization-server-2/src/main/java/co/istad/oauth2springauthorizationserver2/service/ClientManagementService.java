package co.istad.oauth2springauthorizationserver2.service;

import java.util.Set;

public interface ClientManagementService {

     Set<String> updateScopes(String clientId, Set<String> scopes);

}
