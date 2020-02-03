package kg.gulnaz.service;

import kg.gulnaz.model.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class OAuth2AuthenticationService implements AuthenticationService {
    @Autowired
    private OAuth2AuthorizedClientManager oauth2AuthClientManager;

    @Override
    public void authenticate(UserCredential authorizationDetails, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws AuthenticationException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(authorizationDetails.getUsername(), authorizationDetails.getPassword());

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("admin")
                .principal(authentication)
                .attributes(attrs -> {
                    attrs.put(HttpServletRequest.class.getName(), servletRequest);
                    attrs.put(HttpServletResponse.class.getName(), servletResponse);
                })
                .build();

        OAuth2AuthorizedClient authorizedClient = this.oauth2AuthClientManager.authorize(authorizeRequest);

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
        System.out.println(accessToken.getTokenValue());
    }
}
