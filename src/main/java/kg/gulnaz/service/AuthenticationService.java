package kg.gulnaz.service;

import kg.gulnaz.model.UserCredential;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    void authenticate(UserCredential authorizationDetails, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws OAuth2AuthenticationException;
}
