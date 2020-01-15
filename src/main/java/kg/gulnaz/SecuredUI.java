package kg.gulnaz;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;


@SpringUI(path = "/")
@Theme("mytheme")
public class SecuredUI extends UI {
    @Autowired
    private SpringViewProvider viewProvider;
    @Autowired
    private MainView mainView;
    @Autowired
    private LoginView loginView;

    @Override
    public void init(VaadinRequest vaadinRequest) {
        new Navigator(this, this);
        getNavigator().addProvider(viewProvider);
        getNavigator().addView("", mainView);
        getNavigator().addView("login", loginView);
    }
}
