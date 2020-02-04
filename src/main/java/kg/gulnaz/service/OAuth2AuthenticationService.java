package kg.gulnaz.service;

import com.vaadin.spring.annotation.UIScope;
import kg.gulnaz.SecurityContext;
import kg.gulnaz.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@UIScope
public class OAuth2AuthenticationService implements AuthenticationService {
    @Autowired
    private OAuth2AuthorizedClientManager oauth2AuthClientManager;
    @Autowired
    @Qualifier("oauth2ClientDetails")
    private Authentication oauth2ClientDetails;

    @Override
    public void authenticate(UserCredential authorizationDetails, HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        request.setAttribute(OAuth2ParameterNames.USERNAME, authorizationDetails.getUsername());
        request.setAttribute(OAuth2ParameterNames.PASSWORD, new String(authorizationDetails.getPassword()));
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("admin")
                .principal(oauth2ClientDetails)
                .attributes(attrs -> {
                    attrs.put(HttpServletRequest.class.getName(), request);
                    attrs.put(HttpServletResponse.class.getName(), response);
                })
                .build();

        OAuth2AuthorizedClient authorizedClient = this.oauth2AuthClientManager.authorize(authorizeRequest);

        if (authorizedClient == null) {
            System.out.println("Authorized client is null");
            return;
        }
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println(accessToken.getTokenValue());
    }
}
