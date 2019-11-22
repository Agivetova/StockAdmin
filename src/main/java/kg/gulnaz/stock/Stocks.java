package kg.gulnaz.stock;

public class Stocks {

    private Long id;
    private String name;
    private int price;

    public Stocks(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Stocks() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

    @Override
    public Stocks clone() throws CloneNotSupportedException {
        return (Stocks) super.clone();

    }
}
