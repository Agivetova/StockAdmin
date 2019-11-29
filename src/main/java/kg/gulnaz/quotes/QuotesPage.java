package kg.gulnaz.quotes;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;

public class QuotesPage extends VerticalLayout implements View {
    public static final String NAME = "";

    public QuotesPage() {
        Label title = new Label("Menu");

        addComponent(title);
        setComponentAlignment(title, Alignment.TOP_LEFT);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
