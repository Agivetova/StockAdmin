package kg.gulnaz.stock;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.List;

public class StockPage extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String NAME = "OtherSecure";

    private StockService service = StockService.getInstance();
    private Grid<Stock> grid = new Grid<>(Stock.class);
    private TextField filterText = new TextField();

    private StockForm form = new StockForm(this);

    public StockPage() {

        final VerticalLayout layout = new VerticalLayout();

        Button addCustomerBtn = new Button("Add new stock");
        addCustomerBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setStock(new Stock());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addCustomerBtn);

        grid.setColumns("name", "price");

        HorizontalLayout main = new HorizontalLayout(grid, form);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);

        layout.addComponents(main, toolbar);

        updateList();

        addComponent(layout);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
                form.setStock(event.getValue());
            }
        });
    }

    public void updateList() {
        List<Stock> stocks = service.findAll(filterText.getValue());
        grid.setItems(stocks);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
