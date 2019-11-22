package kg.gulnaz.stock;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockService {

    private static StockService instance;
    private static final Logger LOGGER = Logger.getLogger(StockService.class.getName());

    private final HashMap<Long, Stocks> contacts = new HashMap<>();
    private long nextId = 0;

    public StockService() {

    }

    public static StockService getInstance() {
        if (instance == null) {
            instance = new StockService();
            instance.ensureTestData();
        }

        return instance;
    }

    public synchronized long count() {
        return contacts.size();
    }

    public synchronized void delete(Stocks value) {
        contacts.remove(value.getId());
    }

    public synchronized void save(Stocks entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE, "Stock is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Stocks) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

    public void ensureTestData() {

    }
}
