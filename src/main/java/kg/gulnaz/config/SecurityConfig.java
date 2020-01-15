package kg.gulnaz.config;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;

@EnableWebSecurity
@Configuration
@EnableVaadin
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientRepository authorizedClientRepository;
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Autowired
    private SessionAuthenticationStrategy sessionAuthenticationStrategy;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Client()
                .clientRegistrationRepository(clientRegistrationRepository)
                .authorizedClientRepository(authorizedClientRepository)
                .authorizedClientService(authorizedClientService)
                //.and().addFilter(oauth2requestFilter())
                .and()
                .oauth2Login().loginPage("/login").defaultSuccessUrl("/")
        ;
        http.sessionManagement().sessionAuthenticationStrategy(sessionAuthenticationStrategy);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                // Vaadin Flow static resources //
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest //
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",

                // (development mode) static resources //
                "/frontend/**",

                // (development mode) webjars //
                "/webjars/**",

                // (production mode) static resources //
                "/frontend-es5/**", "/frontend-es6/**"
        );
    }

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
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .tokenUri(accessTokenUri)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .scope("read, write")
                    .redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
                    .authorizationUri(authorizationUri)
                    .build();
        }
    }
}

