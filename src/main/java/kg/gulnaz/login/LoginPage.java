package kg.gulnaz.login;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kg.gulnaz.LoginUI;
import kg.gulnaz.register.RegisterPage;

@SpringView(ui = LoginUI.class)
public class LoginPage extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "login";


    public LoginPage() {
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Panel panel = buildLoginPanel();
        addComponent(panel);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

    private Panel buildLoginPanel() {
        Panel panel = new Panel("Login");
        panel.setSizeUndefined();
        FormLayout content = buildForm();
        panel.setContent(content);
        return panel;
    }

    private FormLayout buildForm() {
        FormLayout form = new FormLayout();
        TextField username = new TextField("Username");
        form.addComponent(username);
        PasswordField password = new PasswordField("Password");
        form.addComponent(password);


        Button loginBtn = new Button("Login");
        loginBtn.addClickListener(this::onSend);
        Button registerLnk = new Button("Register");
        registerLnk.addClickListener(this::onRegisterLinkClick);

        HorizontalLayout buttonsToolbar = new HorizontalLayout(
                loginBtn,
                registerLnk
        );
        form.addComponent(buttonsToolbar);
        form.setSizeUndefined();
        form.setMargin(true);
        return form;
    }

    private void onRegisterLinkClick(Button.ClickEvent  event) {
        System.out.println("Registration clicked");
    }

    private void onSend(Button.ClickEvent event) {
        //                if (MyUI.AUTH.authenticate(username.getValue(), password.getValue())) {
//                    VaadinSession.getCurrent().setAttribute("user", username.getValue());
//                    getUI().getNavigator().navigateTo(MainView.NAME);
//                } else {
//                    Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
//                }

    }
}
