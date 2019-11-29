package kg.gulnaz.stock;

import java.io.Serializable;

public class Stock implements Serializable, Cloneable {

    private Long id;

    private String name = "";

    private String price = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String lastName) {
        this.price = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public boolean isPersisted() {
        return id != null;
    }


    @Override
    public Stock clone() throws CloneNotSupportedException {
        return (Stock) super.clone();
    }

    @Override
    public String toString() {
        return name + " " + price;
    }
}
