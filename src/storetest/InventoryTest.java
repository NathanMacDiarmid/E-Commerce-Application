package storetest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import store.Product;
import store.Inventory;

public class InventoryTest {
    private static Inventory i1;
    private static Product p1;

    @BeforeAll
    public static void init(){
        i1 = new Inventory();
        p1 = new Product("Apples", 1, 2.0f);
    }

    @Test
    public void testAddStock(){
        i1.addStock(p1, 20);
        assertEquals(i1.getStockList().get(0), 20, "Inventory's addStock method is not adding the correct amount of inventory");
        assertEquals(i1.getProduct().get(0).getName(), p1.getName(), "Inventory's addStock method is not adding the product object to the list of products");
    }

    @Test
    public void testRemoveStock(){
        assertEquals(i1.removeStock(1, 20), true, "Inventory's removeStock is not returning the correct value");
        assertEquals(i1.getStockList().get(0), 0, "Inventory's removeStock is not removing the correct amount of stock");
        assertEquals(i1.removeStock(1, 10), false, "Inventory's removeStock is not returning the correct value");
    }

    @Test
    public void testGetStock(){
        i1.addStock(p1, 20);
        assertEquals(i1.getStock(1), 20, "Inventory's getStock method is not returning the correct value");
    }

    @Test
    public void testGetProductInfo(){
        assertEquals(i1.getProductInfo(1), p1, "Inventory's getProductInfo method is not returning the correct value");
    }
}
