package kg.gulnaz.register;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

public class RegisterPage extends VerticalLayout implements View {
    public static final String NAME = "";

    public RegisterPage() {
        Panel panel = new Panel("Register");
        panel.setSizeUndefined();
        addComponent(panel);

        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);
        PasswordField confirm_password = new PasswordField("Confirm Password");
        content.addComponent(confirm_password);

        Button send = new Button("Register");
        send.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
//                if (MyUI.AUTH_2.authorization(username.getValue(), password.getValue())){
//                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
//                    getUI().getNavigator().addView(MainView.NAME, MainView.class);
//                    getUI().getNavigator().addView(StockPage.NAME, StockPage.class);
//                    Page.getCurrent().setUriFragment("!" + MainView.NAME);
//                }
            }
        });

        Button send_2 = new Button("Cancel");
        send_2.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().removeView(RegisterPage.NAME);
                VaadinSession.getCurrent().setAttribute("user", null);
                Page.getCurrent().setUriFragment("Login");
            }
        });

        content.addComponent(new HorizontalLayout(
                send,
                send_2
        ));
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
