package kg.gulnaz.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import kg.gulnaz.register.RegisterPage;

public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";


    public LoginPage() {

        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        addComponent(panel);

        FormLayout content = new FormLayout();
        TextField username = new TextField("Username");
        content.addComponent(username);
        PasswordField password = new PasswordField("Password");
        content.addComponent(password);


        Button send = new Button("Login");
        send.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                /*if (SecuredUI.AUTH.authenticate(username.getValue(), password.getValue())) {
                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
                    getUI().getNavigator().navigateTo(MainView.NAME);
                } else {
                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
                }*/
            }
        });

        Button send_2 = new Button("Register");
        send_2.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getUI().getNavigator().addView(RegisterPage.NAME, RegisterPage.class);
                Page.getCurrent().setUriFragment("!" + RegisterPage.NAME);
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
