package kg.gulnaz.service;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.UIScope;
import kg.gulnaz.model.UserCredential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@UIScope
public class OAuth2AuthenticationService implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(OAuth2AuthenticationService.class);
    @Autowired
    private OAuth2AuthorizedClientManager oauth2AuthClientManager;

    @Override
    public void authenticate(UserCredential authorizationDetails, HttpServletRequest request, HttpServletResponse response) throws OAuth2AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            authentication = new AnonymousAuthenticationToken("anonymous", "anonymouse", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
        }
        request.setAttribute(OAuth2ParameterNames.USERNAME, authorizationDetails.getUsername());
        request.setAttribute(OAuth2ParameterNames.PASSWORD, new String(authorizationDetails.getPassword()));

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("admin")
                .principal(authentication)
                .attributes(attrs -> {
                    attrs.put(HttpServletRequest.class.getName(), request);
                    attrs.put(HttpServletResponse.class.getName(), response);
                })
                .build();

        OAuth2AuthorizedClient authorizedClient = this.oauth2AuthClientManager.authorize(authorizeRequest);

        if (authorizedClient == null) {
            logger.error("Unsucessfull authentication");
            return;
        }
        logger.info("User has successfully authenticated");
        UsernamePasswordAuthenticationToken authenticated =
                new UsernamePasswordAuthenticationToken(authorizedClient.getPrincipalName(), null, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        SecurityContextHolder.getContext().setAuthentication(authenticated);

    }
}
