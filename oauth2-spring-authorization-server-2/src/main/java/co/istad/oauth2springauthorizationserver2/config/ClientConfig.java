package co.istad.oauth2springauthorizationserver2.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

@Configuration
public class ClientConfig {

    @Bean
    CommandLineRunner registerRegistrarClient(
            RegisteredClientRepository registeredClientRepository) {

        return args -> {
            if (registeredClientRepository
                    .findByClientId("registrar-client") != null) {
                return;
            }

            RegisteredClient registrar = RegisteredClient
                    .withId(UUID.randomUUID().toString())
                    .clientId("registrar-client")
                    .clientSecret("{noop}qwer")
                    .clientAuthenticationMethod(
                            ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(
                            AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .scope("client.create")
                    .scope("client.read")
                    .clientSettings(ClientSettings.builder()
                            .requireAuthorizationConsent(false)
                            .build())
                    .build();

            registeredClientRepository.save(registrar);
        };
    }


    @Bean
    CommandLineRunner registerOrderClient(
            RegisteredClientRepository repository,
           PasswordEncoder passwordEncoder,
            @Value("${ORDER_CLIENT_SECRET:local-order-secret}") String secret) {

        return args -> {
            if (repository.findByClientId("order-service") != null) {
                return;
            }

            RegisteredClient client = RegisteredClient
                    .withId(UUID.randomUUID().toString())
                    .clientId("order-service")
                    .clientSecret(passwordEncoder.encode(secret))
                    .clientAuthenticationMethod(
                            ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                    .authorizationGrantType(
                            AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .scope("customer.read")
                    .build();

            repository.save(client);
        };
    }

}