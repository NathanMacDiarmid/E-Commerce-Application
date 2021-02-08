// Nathan MacDiarmid
// Matthew Belanger 101144323

public class Product {

    private String name;
    private int id;
    private float price;

    public Product(String newName, int newId, float newPrice) {

        this.name = newName;
        this.id = newId;
        this.price = newPrice;

    }

    public String getName() {

        return name;

    }

    public int getId() {

        return id;

    }

    public float getPrice() {

        return price;

    }
    
}
