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
            final String[] names = new String[] { "Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                    "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                    "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                    "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                    "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                    "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                    "Jaydan Jackson", "Bernard Nilsen" };
            for (String name : names) {
                String[] split = name.split(" ");
                Stock c = new Stock();
                c.setFirstName(split[0]);
                c.setLastName(split[1]);
                save(c);
            }
        }
    }
}
