package kg.gulnaz.config;

import com.vaadin.server.UICreateEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.internal.SpringViewDisplayRegistrationBean;
import com.vaadin.spring.internal.UIID;
import com.vaadin.spring.server.SpringUIProvider;
import com.vaadin.ui.UI;
import com.vaadin.util.CurrentInstance;
import org.springframework.stereotype.Component;

@Component
public class CustomSpringUIProvider extends SpringUIProvider {
    public CustomSpringUIProvider(VaadinSession vaadinSession) {
        super(vaadinSession);
    }

    @Override
    public UI createInstance(UICreateEvent event) {
        final Class<UIID> key = UIID.class;
        final UIID identifier = new UIID(event);
        CurrentInstance.set(key, identifier);
        try {
            logger.debug(
                    "Creating a new UI bean of class [{}] with identifier [{}]",
                    event.getUIClass().getCanonicalName(), identifier);
            UI ui = getWebApplicationContext().getBean(event.getUIClass());

            getSpringViewDisplayRegistrationBean().setBeanClass(event.getUIClass());

            configureNavigator(ui);
            return ui;
        } finally {
            CurrentInstance.set(key, null);
        }
    }

    private SpringViewDisplayRegistrationBean getSpringViewDisplayRegistrationBean() {
        return getWebApplicationContext().getBean(SpringViewDisplayRegistrationBean.class);

    }
}
