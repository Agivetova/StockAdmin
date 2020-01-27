package kg.gulnaz.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

@Configuration
@PropertySource("classpath:/application.properties")
public class OAuthClientConfig {
    @Value("${security.oauth2.client.clientId}")
    private String clientId;
    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;
    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.userAuthorizationUri}")
    private String authorizationUri;
    @Value("${security.oauth2.resource.tokenInfoUri}")
    private String tokenInfoUri;

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
        return new HttpSessionOAuth2AuthorizedClientRepository();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(clientRegistration());
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new SessionFixationProtectionStrategy();
    }

    @Bean
    public ClientRegistration clientRegistration() {
        return ClientRegistration.withRegistrationId("admin")
                .clientName("Stock Admin")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.POST)
                .tokenUri(accessTokenUri)
                .authorizationUri(authorizationUri)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("read, write")
                .build();
    }
}
