// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

package storetest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import myStore.Product;
import myStore.Inventory;

public class InventoryTest {
    private static Inventory i1;
    private static Inventory i2;
    private static Inventory i3;
    private static Inventory i4;
    private static Product p1;
    private static Product p2;

    /**
     * Initialize attributes
     */
    @BeforeAll
    public static void init(){
        i1 = new Inventory();
        i2 = new Inventory();
        i3 = new Inventory();
        i4 = new Inventory();
        p1 = new Product("Apples", 1, 2.0f);
        p2 = new Product("Bananas", 2, 3.0f);
    }

    /**
     * Test inventory's addStock method by checking if it's adding the Product object to the Product list
     * and adding the stock amount to the StockList
     */
    @Test
    public void testAddStock(){
        i1.addStock(p1, 20);
        assertEquals(i1.getStockList().get(0), 20, "Inventory's addStock method is not adding the correct amount of inventory");
        assertEquals(i1.getProduct().get(0).getName(), p1.getName(), "Inventory's addStock method is not adding the product object to the list of products");

        i1.addStock(p2, 10);
        assertEquals(i1.getStockList().get(1), 10, "Inventory's addStock method is not adding the correct amount of inventory");
        assertEquals(i1.getProduct().get(1).getName(), p2.getName(), "Inventory's addStock method is not adding the product object to the list of products");

        i1.addStock(p2, 0);
        assertEquals(i1.getStockList().get(1), 10, "Inventory's addStock method is not adding the correct amount of inventory");
        assertEquals(i1.getProduct().get(1).getName(), p2.getName(), "Inventory's addStock method is not adding the product object to the list of products");
    }

    /**
     * Test inventory's removeStock method by checking if it's removing the stock amount from the StockList and if it's
     * returning the correct value
     */
    @Test
    public void testRemoveStock(){
        i2.addStock(p1, 20);
        assertEquals(i2.removeStock(1, 20), true, "Inventory's removeStock is not returning the correct value");
        assertEquals(i2.getStockList().get(0), 0, "Inventory's removeStock is not removing the correct amount of stock");
        assertEquals(i2.removeStock(1, 10), false, "Inventory's removeStock is not returning the correct value");

        i2.addStock(p2, 10);
        assertEquals(i2.removeStock(2, 5), true, "Inventory's removeStock is not returning the correct value");
        assertEquals(i2.getStockList().get(1), 5, "Inventory's removeStock is not removing the correct amount of stock");

        assertEquals(i2.removeStock(2, 0), true, "Inventory's removeStock is not returning the correct value");
        assertEquals(i2.getStockList().get(1), 5, "Inventory's removeStock is not removing the correct amount of stock");

        assertEquals(i2.removeStock(2, -10), false, "Inventory's removeStock is not returning the correct value");
    }

    /**
     * Test inventory's getStock method by checking if it's returning the correct amount for the specified product id
     */
    @Test
    public void testGetStock(){
        i3.addStock(p1, 10);
        i3.addStock(p2, 5);
        assertEquals(i3.getStock(1), 10, "Inventory's getStock method is not returning the correct value");
        assertEquals(i3.getStock(2), 5, "Inventory's getStock method is not returning the correct value");
        assertEquals(i3.getStock(5), -1, "Inventory's getStock method is not returning the correct value");
    }

    /**
     * Test inventory's getProductInfo method by checking if it's returning the correct Product object for the specified product id
     */
    @Test
    public void testGetProductInfo(){
        i4.addStock(p1, 10);
        i4.addStock(p2, 5);
        assertEquals(i4.getProductInfo(1), p1, "Inventory's getProductInfo method is not returning the correct object");
        assertEquals(i4.getProductInfo(2), p2, "Inventory's getProductInfo method is not returning the correct object");
        assertEquals(i4.getProductInfo(5), null, "Inventory's getProductInfo method is not returning the correct object");
    }
}
