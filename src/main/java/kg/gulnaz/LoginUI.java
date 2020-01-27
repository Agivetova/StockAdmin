package kg.gulnaz;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI(path = "/login")
@Theme("mytheme")
public class LoginUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new VerticalLayout(new Label("Login form")));
    }
}
