package kg.gulnaz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@UIScope
@SpringView(ui = AppUI.class, name = MainView.NAME)
public class MainView extends VerticalLayout implements View {
    public static final String NAME = "main";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        addComponent(new Label("Main view"));
    }
}
