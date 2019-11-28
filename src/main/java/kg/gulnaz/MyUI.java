package kg.gulnaz;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import kg.gulnaz.login.Authentication;
import kg.gulnaz.login.LoginPage;
import kg.gulnaz.quotes.MainView;
import kg.gulnaz.register.Authorization;

@SpringUI
@Theme("mytheme")
public class MyUI extends UI {
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    Navigator navigator;
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        navigator = new Navigator(this, this);

        navigator.addView(LoginPage.NAME, LoginPage.class);

        navigator.addView(MainView.NAME, MainView.class);

        navigator.navigateTo(LoginPage.NAME);



    }
}
