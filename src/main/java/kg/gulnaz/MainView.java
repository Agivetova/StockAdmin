package kg.gulnaz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;

public class MainView extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String NAME = "Secure";

    HorizontalLayout upperSection = new HorizontalLayout();
    HorizontalLayout innerUpperSection = new HorizontalLayout();
    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    VerticalLayout menuLayout = new VerticalLayout();
    HorizontalLayout menuTitle = new HorizontalLayout();
    VerticalLayout contentLayout = new VerticalLayout();

    Label lblHeader;
    Label lblMenu;
    Button btnLogout;

    public MainView() {

        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnLogout = new Button("Sign Out");
        btnLogout.addStyleName("small");
        btnLogout.addStyleName("friendly");
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

        lblMenu = new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");

        innerUpperSection.addComponent(lblHeader);
        innerUpperSection.addComponent(btnLogout);
        innerUpperSection.setExpandRatio(btnLogout, 1);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.MIDDLE_RIGHT);

        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, false));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        upperSection.setHeight(4, UNITS_EM);

        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        contentLayout.setSizeFull();
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(15);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection, 1);

    }

    public void setMenuTitle() {
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);
    }

    public void addWelcomeText() {
        Label lblTitle = new Label("Welcome " + VaadinSession.getCurrent().getAttribute("user").toString() + "!");
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        lblHeader.setValue("" + VaadinSession.getCurrent().getAttribute("user").toString());

        contentLayout.addComponent(lblTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));
    }

    public void addDashboardOption(String caption) {

        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.removeAllComponents();
                addWelcomeText();
            }
        });
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
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);
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
        addWelcomeText();
    }
}
