package kg.gulnaz.stock;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


public class StockForm extends FormLayout {

    private TextField name = new TextField("Name");
    private TextField price = new TextField("Price");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private StockService service = StockService.getInstance();
    private Stock stock;
    private StockPage stockPage;
    private Binder<Stock> binder = new Binder<>(Stock.class);

    public StockForm(StockPage stockPage) {
        this.stockPage = stockPage;

        setSizeUndefined();
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(name, price, buttons);

        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }

    public void setStock(Stock stock) {
        this.stock = stock;
        binder.setBean(stock);

        delete.setVisible(stock.isPersisted());
        setVisible(true);
        name.selectAll();
    }

    private void delete() {
        service.delete(stock);
        stockPage.updateList();
        setVisible(false);
    }

    private void save() {
        service.save(stock);
        stockPage.updateList();
        setVisible(false);
    }
}
