package kg.gulnaz;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadinNavigation;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import kg.gulnaz.login.LoginView;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI(path = "/login")
@Theme("mytheme")
@SpringViewDisplay
public class LoginUI extends UI {
    @Autowired
    private SpringViewProvider springViewProvider;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();

        Navigator navigator = new Navigator(this, mainLayout);
        navigator.addProvider(springViewProvider);
        setNavigator(navigator);
        getNavigator().navigateTo(LoginView.NAME);

        setContent(mainLayout);
    }
}
