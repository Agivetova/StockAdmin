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
    public void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.addComponent(new Label("login form"));
        setContent(layout);
    }
}
