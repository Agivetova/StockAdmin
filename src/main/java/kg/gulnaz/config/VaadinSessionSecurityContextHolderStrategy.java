package kg.gulnaz.config;

import com.vaadin.server.VaadinSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * A custom {@link SecurityContextHolderStrategy} that stores the {@link SecurityContext} in the Vaadin Session.
 */
public class VaadinSessionSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

    @Override
    public void clearContext() {
        getCurrentSession().ifPresent(session -> session.setAttribute(SecurityContext.class, null));
    }

    @Override
    public SecurityContext getContext() {
        VaadinSession currentSession = getCurrentSession().orElse(null);
        if (currentSession == null) {
            return createEmptyContext();
        }
        SecurityContext context = currentSession.getAttribute(SecurityContext.class);
        if (context == null) {
            context = createEmptyContext();
            currentSession.setAttribute(SecurityContext.class, context);
        }
        return context;
    }

    @Override
    public void setContext(SecurityContext context) {
        Assert.notNull(context, "Only non-null SecurityContext instances are permitted");
        getCurrentSession().ifPresent(session -> session.setAttribute(SecurityContext.class, context));
    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }

    private Optional<VaadinSession> getCurrentSession() {
        return Optional.ofNullable(VaadinSession.getCurrent());
    }
}
