package kg.gulnaz;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import kg.gulnaz.login.Authentication;
import kg.gulnaz.login.LoginPage;
import kg.gulnaz.quotes.MainView;
import kg.gulnaz.register.Authorization;
import kg.gulnaz.stock.StockPage;


@Theme("mytheme")
public class MyUI extends UI {
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    public static Authentication AUTH;
    public static Authorization AUTH_2;
    Navigator navigator;
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        AUTH = new Authentication();
        AUTH_2 = new Authorization();
        navigator = new Navigator(this, this);

        getNavigator().addView(LoginPage.NAME, LoginPage.class);
        getNavigator().setErrorView(LoginPage.class);

        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });

    }

    private void router(String route) {

        if (getSession().getAttribute("user") != null) {
            getNavigator().addView(MainView.NAME, MainView.class);
            getNavigator().addView(StockPage.NAME, StockPage.class);

            if (route.equals("!OtherSecure")) {
                getNavigator().navigateTo(StockPage.NAME);
            } else {
                getNavigator().navigateTo(MainView.NAME);
            }
        } else {
            getNavigator().navigateTo(LoginPage.NAME);
        }
    }
}
