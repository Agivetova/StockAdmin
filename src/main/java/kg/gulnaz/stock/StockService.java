package kg.gulnaz.stock;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StockService {

    private static StockService instance;
    private static final Logger LOGGER = Logger.getLogger(StockService.class.getName());

    private final HashMap<Long, Stock> contacts = new HashMap<>();
    private long nextId = 0;

    private StockService() {
    }

    public static StockService getInstance() {
        if (instance == null) {
            instance = new StockService();
            instance.ensureTestData();
        }
        return instance;
    }

    public synchronized List<Stock> findAll() {
        return findAll(null);
    }

    public synchronized List<Stock> findAll(String stringFilter) {
        ArrayList<Stock> arrayList = new ArrayList<>();
        for (Stock contact : contacts.values()) {
            try {
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
                if (passesFilter) {
                    arrayList.add(contact.clone());
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(StockService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Collections.sort(arrayList, new Comparator<Stock>() {

            @Override
            public int compare(Stock o1, Stock o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public synchronized long count() {
        return contacts.size();
    }

    public synchronized void delete(Stock value) {
        contacts.remove(value.getId());
    }

    public synchronized void save(Stock entry) {
        if (entry == null) {
            LOGGER.log(Level.SEVERE,
                    "Stock is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
            return;
        }
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Stock) entry.clone();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        contacts.put(entry.getId(), entry);
    }

    public void ensureTestData() {
        if (findAll().isEmpty()) {
            final String[] names = new String[] { "Amazon.com 1,818.51", "Coca-Cola 53.95", "Facebook 202.00",
                    "Intel 58.51", "Microsoft 152.32", "Walmart 118.76", "Pfizer 38.63", "Procter&Gamble 121.76", "Verizon 60.10", "Booking 1,906.45", "AT&T 37.66", "Merck&Co 87.61", "Amgen 234.54" };
            for (String name : names) {
                String[] split = name.split(" ");
                Stock c = new Stock();
                c.setName(split[0]);
                c.setPrice(split[1]);
                save(c);
            }
        }
    }
}
