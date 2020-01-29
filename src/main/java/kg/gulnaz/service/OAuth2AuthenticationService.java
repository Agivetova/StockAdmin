package kg.gulnaz.service;

import kg.gulnaz.model.UserCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OAuth2AuthenticationService implements AuthenticationService {
    @Override
    public void authenticate(UserCredential authorizationDetails) throws AuthenticationException {
        System.out.println("Authenticating user " + authorizationDetails.getUsername() + "");
        log.info("Authentication user {}", authorizationDetails.getUsername());
    }
}
