package kg.gulnaz;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import kg.gulnaz.login.LoginPage;

@SpringUI(path = "/login")
@Theme("mytheme")
public class LoginUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        getUI().getNavigator().navigateTo(LoginPage.NAME);
    }
}
