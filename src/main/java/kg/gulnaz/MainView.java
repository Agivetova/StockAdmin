package kg.gulnaz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kg.gulnaz.quotes.QuotesPage;
import kg.gulnaz.stock.StockPage;

public class MainView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String NAME = "Secure";


    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    CssLayout menuLayout = new CssLayout();
    VerticalLayout contentLayout = new VerticalLayout();

    Label lblHeader;
    Label title;
    Button btnLogout;

    public MainView() {

        title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnLogout = new Button("Sign Out");
        btnLogout.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ITEM);
        btnLogout.setSizeUndefined();
        btnLogout.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                getUI().getNavigator().removeView(MainView.NAME);
                getUI().getNavigator().removeView(StockPage.NAME);
                VaadinSession.getCurrent().setAttribute("user", null);
                Page.getCurrent().setUriFragment("Login");
            }
        });

        menuLayout.addComponent(title);
        menuLayout.addComponent(btnLogout);

        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        contentLayout.setSizeFull();
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(10);
        addComponent(lowerSection);

        setSizeFull();

        setMargin(false);

        setExpandRatio(lowerSection, 1);

    }

    public void setMenuTitle() {
        menuLayout.addComponent(title);
        menuLayout.setWidth("100%");
    }

    public void setSignOut() {
        menuLayout.addComponent(btnLogout);
    }

    public void addWelcomeText() {
        Label lblTitle = new Label("Welcome " + VaadinSession.getCurrent().getAttribute("user").toString() + "!");

        lblHeader.setValue("" + VaadinSession.getCurrent().getAttribute("user").toString());

        contentLayout.addComponent(lblTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));
    }

    public Component getComponent(String componentName) {
        if (componentName.equals("StockPage")) {
            return new StockPage();
        } else {
            return new QuotesPage();
        }
    }

    public void addMenuOption(String caption, String componentName) {
        Button button = new Button(caption);
        button.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        menuLayout.addComponent(button);
        menuLayout.addStyleName(ValoTheme.MENU_ROOT);
        button.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                contentLayout.removeAllComponents();
                contentLayout.addComponent(getComponent(componentName));
            }
        });
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        setMenuTitle();
        this.addMenuOption("Stock", "StockPage");
        this.addMenuOption("Quotes", "QuotesPage");
        setSignOut();
        addWelcomeText();
    }
}
