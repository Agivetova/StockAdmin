package kg.gulnaz;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;

@SpringUI(path = "/")
@Theme("mytheme")
public class AppUI extends UI {
    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();

        Navigator navigator = new Navigator(this, mainLayout);
        navigator.addProvider(springViewProvider);
        setNavigator(navigator);
        getNavigator().navigateTo(MainView.NAME);

        setContent(mainLayout);
    }

    @WebServlet(urlPatterns = "/*", name = "SecuredUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = AppUI.class)
    @Push
    public static class SecuredUIServlet extends SpringVaadinServlet {
    }
}
