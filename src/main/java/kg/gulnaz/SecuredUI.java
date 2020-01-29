package kg.gulnaz;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebServlet;


@SpringUI
@Theme("mytheme")
@EnableVaadinNavigation
public class SecuredUI extends UI {
    @Override
    public void init(VaadinRequest vaadinRequest) {
        setContent(new Label("MAIN UI"));
    }

    @WebServlet(urlPatterns = "/*", name = "SecuredUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = SecuredUI.class)
    @Push
    public static class SecuredUIServlet extends SpringVaadinServlet {
    }
}
