// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

import java.util.ArrayList;

public class StoreManager {
    private static int cardIDs = 0;
    private Inventory inventory;
    private ArrayList<ShoppingCart> shoppingCarts;

    /**
     * default constructor.
     */
    public StoreManager() {
        this.inventory = new Inventory();
        this.shoppingCarts = new ArrayList<ShoppingCart>();
    }

    /**
     * This method is made to keep the Inventory private but still accessible.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Returns the stock for a given product ID
     */
    public int managerGetStock(int productID) {
        return this.inventory.getStock(productID);
    }

    /**
     * This method processes transactions, its parameter is a 2D ArrayList where every ArrayList
     * inside the ArrayList represents a product. These ArrayLists inside the main ArrayList consist
     * of 2 integers. The first integer represents the product ID and the second represents the stock
     * amount. This method will then subtract the stock from the inventory if possible and then return
     * the total cost if successful or -1 if unsuccessful.
     */
    public float transactionTotal(ArrayList<ArrayList<Integer>> shoppingCart) {
        float priceTotal = 0;
        float amount;

        for (int i = 0; i < shoppingCart.size(); i++){
            amount = managerGetStock(shoppingCart.get(i).get(0));
            if (amount < shoppingCart.get(i).get(1)) {
                return -1;
            }
        }

        for (int i = 0; i < shoppingCart.size(); i++){
            priceTotal += this.inventory.getProductInfo(shoppingCart.get(i).get(0)).getPrice() * shoppingCart.get(i).get(1);
            boolean removalSuccess = this.inventory.removeStock(shoppingCart.get(i).get(0), shoppingCart.get(i).get(1));
        }
        return priceTotal;
    }

    /**
     * method to create a new ShoppingCart and add it too the shoppingCarts and return the unique
     * ID that represents that ShoppingCart
     * @return int
     */
    public int assignNewCartID(){
        ShoppingCart sc = new ShoppingCart();
        shoppingCarts.add(sc);
        return cardIDs++;
    }

    /**
     * This method removes the required amount of product from inventory and adds it to the specified
     * cart, will return true if successful and false otherwise
     * @param cartID
     * @param productID
     * @param productAmount
     * @return boolean
     */
    public boolean cartAddProduct(int cartID, int productID, int productAmount){
        if(this.inventory.removeStock(productID, productAmount) == true){
            this.shoppingCarts.get(cartID).addStock(this.inventory.getProductInfo(productID), productAmount);
            return true;
        }
        return false;
    }

    /**
     * This method removes the required amount of product from the specified cart and adds it to the
     * inventory, will return true if successful and false otherwise
     * @param cartID
     * @param productID
     * @param productAmount
     * @return boolean
     */
    public boolean cartRemoveProduct(int cartID, int productID, int productAmount){
        if(this.shoppingCarts.get(cartID).removeStock(productID, productAmount) == true){
            this.inventory.addStock(this.inventory.getProductInfo(productID), productAmount);
            return true;
        }
        return false;
    }


}