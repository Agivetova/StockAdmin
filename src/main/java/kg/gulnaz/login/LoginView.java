package kg.gulnaz.login;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinServletResponse;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import kg.gulnaz.LoginUI;
import kg.gulnaz.model.UserCredential;
import kg.gulnaz.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;

@UIScope
@SpringView(ui = LoginUI.class, name = LoginView.NAME)
public class LoginView extends VerticalLayout implements View {
    private static final long serialVersionUID = 1L;
    public static final String NAME = "";

    private Binder<UserCredential> loginDataBinder;
    @Autowired
    private AuthenticationService authService;

    public LoginView() {
        loginDataBinder = new Binder<>();
        loginDataBinder.readBean(new UserCredential());
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

        TextField usernameField = new TextField("Username");
        loginDataBinder
                .forField(usernameField)
                .asRequired("Please put your username")
                .bind(UserCredential::getUsername, UserCredential::setUsername);
        form.addComponent(usernameField);
        PasswordField passwordField = new PasswordField("Password");
        loginDataBinder.forField(passwordField)
                .asRequired("Please provide password")
                .bind(
                    credential -> "",
                    (credential, value) -> credential.setPassword(value.toCharArray())
                );
        form.addComponent(passwordField);


        Button loginBtn = new Button("Login");
        loginBtn.addClickListener(this::onLoginClick);
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

    private void onLoginClick(Button.ClickEvent event) {
        UserCredential authorizationDetails = new UserCredential();
        VaadinServletRequest request = VaadinServletRequest.getCurrent();
        VaadinServletResponse response = VaadinServletResponse.getCurrent();
        try {
            loginDataBinder.writeBean(authorizationDetails);
            authService.authenticate(authorizationDetails, request, response);
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (OAuth2AuthorizationException ex) {
            Notification.show(ex.getError().getErrorCode(), ex.getError().getDescription(), Notification.Type.ERROR_MESSAGE);
        }
    }
}
