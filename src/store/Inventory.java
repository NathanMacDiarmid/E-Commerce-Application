package store;
// Matthew Belanger 101144323
// Nathan MacDiarmid 101098993

import org.relaxng.datatype.DatatypeException;

import java.util.ArrayList;


public class Inventory {

    /**
     * Attributes are two ArrayLists, one to hold the store.Product objects and the other to hold the
     * corresponding stock.
     */
    private ArrayList<Product> inventoryProducts = null;
    private ArrayList<Integer> inventoryStock = null;

    /**
     * main constructor
     */
    public Inventory(){
        this.inventoryProducts = new ArrayList<>();
        this.inventoryStock = new ArrayList<>();
    }

    /**
     * If the store.Product object is already in the inventory then this method increases the stock,
     * if it is not then the store.Product object is added along with the stock.
     *
     * Raises errors if stockToAdd is negative and if the name of the product is null.
     */
    public void addStock(Product product, int stockToAdd){
        try {
            if (stockToAdd < 0) {
                throw new IllegalArgumentException(" - cannot add negative product, please use the removeStock method.");
            }
            if (product.getName() == null) {
                throw new NullPointerException(" is an invalid product.");
            }
            else {
                boolean inInventory = false;
                int previousStock = 0;
                for (int i = 0; i < inventoryProducts.size(); i++){
                    if (inventoryProducts.get(i).getId() == product.getId()){
                        inInventory = true;
                        previousStock = inventoryStock.get(i);
                        inventoryStock.set(i, previousStock + stockToAdd);
                    }
                }
                if(!inInventory){
                    inventoryProducts.add(product);
                    inventoryStock.add(stockToAdd);
                }
            }
        }
        catch (IllegalArgumentException | NullPointerException e1) {
            System.out.println(product.getName() + e1.getMessage());
        }
    }

    /**
     * Will remove stock for the corresponding product ID, if successful will return true, if
     * unsuccessful will return false.
     *
     * Raise an error if stockToRemove is negative.
     */
    public boolean removeStock(int productID, int stockToRemove) {
        try {
            if (stockToRemove < 0) {
                throw new IllegalArgumentException(" - cannot remove a negative number of stock.");
            }
            else {
                int previousStock = 0;
                for (int i = 0; i < inventoryProducts.size(); i++) {
                    if (inventoryProducts.get(i).getId() == productID) {
                        if ((inventoryStock.get(i) - stockToRemove) >= 0) {
                            previousStock = inventoryStock.get(i);
                            inventoryStock.set(i, previousStock - stockToRemove);
                            return true;
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e1) {
            System.out.println("Product with id (" + productID + ")" + e1.getMessage());
        }

        return false;
    }

    /**
     * Will return stock amount for product ID
     *
     * Raises a DatatypeException if the product id is not linked to a product
     * ie. the product doesn't exist
     */
    public int getStock(int productID){
        try {
            for (int i = 0; i < inventoryProducts.size(); i++){
                if (inventoryProducts.get(i).getId() == productID){
                    return inventoryStock.get(i);
                }
                else {
                    throw new DatatypeException(" - this product does not exist.");
                }
            }
        }
        catch (DatatypeException e) {
            System.out.println("Product with id (" + productID + ")" + e.getMessage());
        }

        return -1;
    }

    /**
     * returns the inventoryStock ArrayList
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getStockList() {
        return this.inventoryStock;
    }

    /**
     * returns the inventoryProducts ArrayList
     * @return ArrayList<store.Product>
     */
    public ArrayList<Product> getProduct() {
        return this.inventoryProducts;
    }

    /**
     * Will return product object for product ID
     *
     * Raises a DatatypeException if the product id is not linked to a product
     * ie. the product doesn't exist
     */
    public Product getProductInfo(int productID){
        try {
            for (Product inventoryProduct : inventoryProducts) {
                if (inventoryProduct.getId() == productID) {
                    return inventoryProduct;
                }
                else {
                    throw new DatatypeException(" - this product does not exist.");
                }
            }
        }
        catch (DatatypeException e) {
            System.out.println("Product with id (" + productID + ")" + e.getMessage());
        }
        return null;
    }

    /**
     * Prints all of the elements in the inventoryProducts ArrayList (mainly for testing)
     */
    public void printProducts(){
        for (Product p : inventoryProducts){
            System.out.println(p.getName() + " " + p.getId() + " " + p.getPrice());
        }
    }

    /**
     * Prints all of the elements in the inventoryStock ArrayList (mainly for testing)
     */
    public void printStock(){
        for (Integer i : inventoryStock){
            System.out.println(i);
        }
    }





}
