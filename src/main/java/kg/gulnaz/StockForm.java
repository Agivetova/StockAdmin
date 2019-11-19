package kg.gulnaz;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;


public class StockForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextField price = new TextField("Price");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<Stocks> binder = new Binder<>(Stocks.class);

    private StockService service = StockService.getInstance();
    private Stocks stocks;
    private StockPage stockPage;

    public StockForm(StockPage stockPage) {

        this.stockPage = stockPage;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(name, price, buttons);
    }

    public void setStock(Stocks stocks) {
        this.stocks = stocks;
        binder.setBean(stocks);
        delete.setVisible(stocks.isPersisted());
        setVisible(true);
        name.selectAll();
    }

    private void delete() {
        service.delete(stocks);
        stockPage.updateList();
        setVisible(false);
    }

    private void save() {
        service.save(stocks);
        stockPage.updateList();
        setVisible(false);
    }
}
