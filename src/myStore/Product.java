package myStore;
// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

public class Product {

    private String name;
    private int id;
    private float price;

    /**
     * store.Main Constructor
     */
    public Product(String newName, int newId, float newPrice) {

        if (newId < 0 || newPrice < 0) {
            throw new IllegalArgumentException("Cannot have negative id or price.");
        }

        else {
            this.name = newName;
            this.id = newId;
            this.price = newPrice;
        }

    }

    /**
     * Returns name of product
     */
    public String getName() {

        return name;

    }

    /**
     * Returns ID of product
     */
    public int getId() {

        return id;

    }

    /**
     * Returns price of product
     */
    public float getPrice() {

        return price;

    }
    
}
