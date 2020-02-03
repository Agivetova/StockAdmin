package kg.gulnaz.service;

import kg.gulnaz.model.UserCredential;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    void authenticate(UserCredential authorizationDetails) throws AuthenticationException;
}
