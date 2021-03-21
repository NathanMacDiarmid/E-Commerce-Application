package store;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        Product p1 = new Product("apples", 1, 2.0f);
        sm.getInventory().addStock(p1, 30);
        sm.getInventory().getStock(1);

    }
}
