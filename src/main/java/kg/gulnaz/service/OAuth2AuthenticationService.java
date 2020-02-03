package kg.gulnaz.service;

import kg.gulnaz.model.UserCredential;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class OAuth2AuthenticationService implements AuthenticationService {
    @Override
    public void authenticate(UserCredential authorizationDetails) throws AuthenticationException {
        System.out.println("Authenticating user " + authorizationDetails.getUsername() + "");
    }
}
