package kg.gulnaz.config;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.internal.UIScopeImpl;
import com.vaadin.spring.internal.VaadinSessionScope;
import com.vaadin.spring.server.SpringUIProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import kg.gulnaz.LoginUI;
import kg.gulnaz.SecuredUI;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

@EnableVaadin
@Configuration
public class ApplicationConfig {
    static {
        // Use a custom SecurityContextHolderStrategy
        SecurityContextHolder.setStrategyName(VaadinSessionSecurityContextHolderStrategy.class.getName());
    }

    /*@WebServlet(urlPatterns = "/login", name = "LoginServlet", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
    @Push
    public static class LoginServlet extends SpringServlet {

    }

    @WebServlet(urlPatterns = "/app", name = "AppServlet", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = SecuredUI.class)
    @Push
    public static class AppServlet extends SpringServlet {

    }*/


    public static class SpringServlet extends SpringVaadinServlet {
        @Override
        protected void servletInitialized() throws ServletException {
            VaadinServletService service = getService();
            service.addSessionInitListener(new SessionInitListener() {

                private static final long serialVersionUID = -6307820453486668084L;

                @Override
                public void sessionInit(SessionInitEvent sessionInitEvent)
                        throws ServiceException {
                    WebApplicationContext webApplicationContext = WebApplicationContextUtils
                            .getWebApplicationContext(getServletContext());

                    // remove DefaultUIProvider instances to avoid mapping
                    // extraneous UIs if e.g. a servlet is declared as a nested
                    // class in a UI class
                    VaadinSession session = sessionInitEvent.getSession();
                    List<UIProvider> uiProviders = new ArrayList<UIProvider>(
                            session.getUIProviders());
                    for (UIProvider provider : uiProviders) {
                        // use canonical names as these may have been loaded with
                        // different classloaders
                        if (DefaultUIProvider.class.getCanonicalName().equals(
                                provider.getClass().getCanonicalName())) {
                            session.removeUIProvider(provider);
                        }
                    }

                    // add Spring UI provider
                    SpringUIProvider uiProvider = new CustomSpringUIProvider(session);
                    session.addUIProvider(uiProvider);
                }
            });
            service.addSessionDestroyListener(new SessionDestroyListener() {
                @Override
                public void sessionDestroy(SessionDestroyEvent event) {
                    VaadinSession session = event.getSession();

                    UIScopeImpl.cleanupSession(session);
                    VaadinSessionScope.cleanupSession(session);
                }
            });
        }
    }
}
