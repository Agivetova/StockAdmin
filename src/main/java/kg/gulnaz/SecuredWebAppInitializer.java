package kg.gulnaz;

import kg.gulnaz.config.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

@WebListener
public class SecuredWebAppInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecuredWebAppInitializer() {
        super(SecurityConfig.class);

    }

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);
        servletContext.addListener(new HttpSessionEventPublisher());
    }
}
