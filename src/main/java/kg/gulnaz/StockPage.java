package kg.gulnaz;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import java.util.Arrays;
import java.util.List;

public class StockPage extends VerticalLayout implements View {

    private static final long serialVersionUID = 1L;
    public static final String NAME = "OtherSecure";

    private StockForm stockForm = new StockForm(this);

    public StockPage() {

        List<Stocks> list = Arrays.asList(
                new Stocks( "Nicolaus Copernicus", 1543),
                new Stocks( "Galileo Galilei", 1564),
                new Stocks( "Johannes Kepler", 1571)
        );

        Grid<Stocks> stocksGrid = new Grid<>();

        stocksGrid.setHeight("300px");
        Button addNewStock = new Button("Add");
        addNewStock.addClickListener(e -> {
            stocksGrid.asSingleSelect().clear();
            stockForm.setStock(new Stocks());
        });

        HorizontalLayout toolbar = new HorizontalLayout(addNewStock);

        stocksGrid.setHeight(300, Unit.PIXELS);
        stocksGrid.setItems(list);
        stocksGrid.addColumn(Stocks::getName).setCaption("Name");
        stocksGrid.addColumn(Stocks::getPrice).setCaption("Price");

        VerticalLayout main = new VerticalLayout(stocksGrid, stockForm);

        main.setSizeFull();
        main.setExpandRatio(stocksGrid, 1);

        addComponents(toolbar, main);

        updateList();
        stockForm.setVisible(false);

        stocksGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                stockForm.setVisible(false);
            } else {
                stockForm.setStock(event.getValue());
            }
        });

    }

    public void updateList() {
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}
