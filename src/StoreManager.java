// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

import java.util.ArrayList;

public class StoreManager {
    private static int cartIDs = 0;
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
     * This method returns the total price of items in a specified cart
     * @param cartID
     * @return float
     */
    public float getPrice(int cartID){
        float priceTotal = 0;

        for(int i = 0; i < this.shoppingCarts.get(cartID).getProduct().size(); i++){
            priceTotal += this.shoppingCarts.get(cartID).getProduct().get(i).getPrice() * this.shoppingCarts.get(cartID).getStock().get(i);
        }

        return priceTotal;
    }

    /**
     * Method to help the StoreView by printing all of the contents in a specified shopping cart
     * @param cartID
     */
    public void printCart(int cartID){
        System.out.println("CART ID: " + cartID);

        System.out.println("|-------------------------CART CONTENTS-------------------------|");

        System.out.println("Amount | Product Name | Unit Price | Product ID");

        for (int i = 0; i < this.shoppingCarts.get(cartID).getProduct().size(); i++) {
            String s = String.format(
                    "%d | %s | %.2f | (%d)\n",
                    this.shoppingCarts.get(cartID).getStock().get(i),
                    this.shoppingCarts.get(cartID).getProduct().get(i).getName(),
                    this.shoppingCarts.get(cartID).getProduct().get(i).getPrice(),
                    this.shoppingCarts.get(cartID).getProduct().get(i).getId()
            );
            System.out.println(s);
        }
    }

    /**
     * method to create a new ShoppingCart and add it too the shoppingCarts and return the unique
     * ID that represents that ShoppingCart
     * @return int
     */
    public int assignNewCartID(){
        ShoppingCart sc = new ShoppingCart();
        this.shoppingCarts.add(sc);
        return cartIDs++;
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
        if(this.inventory.removeStock(productID, productAmount)){
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

    /**
     * this method returns all of the products in a cart back to the inventory
     * @param cartID
     */
    public void cartQuit(int cartID){
        for(int i = 0; i < this.shoppingCarts.get(cartID).getProduct().size(); i++){
            this.inventory.addStock(this.shoppingCarts.get(cartID).getProduct().get(i), this.shoppingCarts.get(cartID).getStock().get(i));
        }
    }


}