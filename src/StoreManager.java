// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

import java.util.ArrayList;

public class StoreManager {

    /**
     * Initializes two new ArrayLists from Inventory class.
     */

    private Inventory inventory = null;
    private String name;

    /**
     * Main constructor.
     */

    public StoreManager(String iName) {
        this.name = iName;
        this.inventory = new Inventory();
    }

    /**
     * This method is made to keep the Inventory private but still accessible.
     */

    public Inventory getInventory() {

        return this.inventory;

    }

    public int managerGetStock(int productID) {

        return this.inventory.getStock(productID);

    }

    public float transactionTotal(ArrayList<ArrayList<Integer>> product) {
        float priceTotal = 0;
        float amount;

        for (int i = 0; i < product.size(); i++){
            for (int j = 0; j < product.get(i).size(); j++) {

                amount = managerGetStock(product.get(i).get(0));

                if (amount < product.get(i).get(1)) {
                    return -1;
                }

                else {

                    priceTotal += this.inventory.getProductInfo(product.get(i).get(0)).getPrice() * product.get(i).get(1) / product.size();
                    boolean removalSuccess = this.inventory.removeStock(product.get(i).get(0), product.get(i).get(1));

                }

            }
        }
        return priceTotal;
    }

}
