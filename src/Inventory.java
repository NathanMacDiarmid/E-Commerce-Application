// Matthew Belanger 101144323
// Nathan MacDiarmid

import java.util.ArrayList;


public class Inventory {

    private ArrayList<Product> inventoryProducts = null;
    private ArrayList<Integer> inventoryStock = null;

    public Inventory(){
        this.inventoryProducts = new ArrayList<>();
        this.inventoryStock = new ArrayList<>();
    }

    public void addStock(Product product, int stockToAdd){
        boolean inInventory = false;
        int previousStock = 0;
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i) == product){
                inInventory = true;
                previousStock = inventoryStock.get(i);
                inventoryStock.set(i, previousStock + stockToAdd);
            }
        }
        if(inInventory == false){
            inventoryProducts.add(product);
            inventoryStock.add(stockToAdd);
        }
    }

    public boolean removeStock(int productID, int stockToRemove){
        int previousStock = 0;
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i).getId() == productID){
                if((inventoryStock.get(i) - stockToRemove) > 0){
                    previousStock = inventoryStock.get(i);
                    inventoryStock.set(i, previousStock - stockToRemove);
                    return true;
                }
            }
        }
        return false;
    }

    public int getStock(int productID){
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i).getId() == productID){
                return inventoryStock.get(i);
            }
        }
        return -1;
    }

    public Product getProductInfo(int productID){
        for (int i = 0; i < inventoryProducts.size(); i++){
            if (inventoryProducts.get(i).getId() == productID){
                return inventoryProducts.get(i);
            }
        }
        return null;
    }

    public void printProducts(){
        for (Product p : inventoryProducts){
            System.out.println(p.getName() + " " + p.getId() + " " + p.getPrice());
        }
    }

    public void printStock(){
        for (Integer i : inventoryStock){
            System.out.println(i);
        }
    }





}
