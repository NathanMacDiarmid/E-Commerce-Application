package myStore;

public interface ProductStockContainer {

    public int getProductQuantity(Product product);

    public void addProductQuantity(Product product, int amount);

    public boolean removeProductQuantity(Product product, int amount);

    public int getNumOfProducts();

}
