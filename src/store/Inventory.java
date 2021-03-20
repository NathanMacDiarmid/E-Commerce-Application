package store;
// Matthew Belanger 101144323
// Nathan MacDiarmid 101098993

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
     * If the store.Product object is already in the inventory then this method just increases the stock,
     * if it is not then the store.Product object is added along with the stock.
     */
    public void addStock(Product product, int stockToAdd){
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

    /**
     * Will remove stock for the corresponding product ID, if successful will return true, if
     * unsuccessful will return false.
     */
    public boolean removeStock(int productID, int stockToRemove){
        int previousStock = 0;
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i).getId() == productID){
                if((inventoryStock.get(i) - stockToRemove) >= 0){
                    previousStock = inventoryStock.get(i);
                    inventoryStock.set(i, previousStock - stockToRemove);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Will return stock amount for product ID
     */
    public int getStock(int productID){
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i).getId() == productID){
                return inventoryStock.get(i);
            }
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
     */
    public Product getProductInfo(int productID){
        for (Product inventoryProduct : inventoryProducts) {
            if (inventoryProduct.getId() == productID) {
                return inventoryProduct;
            }
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
