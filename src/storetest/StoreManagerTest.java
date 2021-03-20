package storetest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import store.Product;
import store.StoreManager;

public class StoreManagerTest {
    private static StoreManager s1;
    private static Product p1;
    private static int c1;

    @BeforeAll
    public static void init(){
        s1 = new StoreManager();
        p1 = new Product("Apples", 1, 2.0f);
        c1 = s1.assignNewCartID();
    }

    @Test
    public void testAssignNewCartID(){
        assertEquals(c1, 0, "StoreManager's assignNewCartID method is not working");
    }

    @Test
    public void testCartAddProduct(){
        assertEquals(s1.cartAddProduct(c1, 1, 10), false, "StoreManager's cartAddProduct method is not returning proper value");
        s1.getInventory().addStock(p1, 10);
        assertEquals(s1.cartAddProduct(c1, 1, 10), true, "StoreManager's cartAddProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getStock(1), 10, "StoreManager's cartAddProduct method is not adding products to cart");
        assertEquals(s1.getInventory().getStock(1), 0, "StoreManager's cartAddProduct method is not removing products from inventory");
    }

    @Test
    public void testGetPrice(){
        s1.getInventory().addStock(p1, 10);
        s1.cartAddProduct(c1, 1, 10);
        assertEquals(s1.getPrice(c1), 20.0f, "StoreManager's getPrice method is not returning proper price");
    }

    @Test
    public void testCartRemoveProducts(){
        assertEquals(s1.cartRemoveProduct(c1, 1, 10), false, "StoreManager's cartRemoveProduct method is not returning proper value");
        s1.getInventory().addStock(p1, 10);
        s1.cartAddProduct(c1, 1, 10);
        assertEquals(s1.cartRemoveProduct(c1, 1, 10), true, "StoreManager's cartRemoveProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getStock(1), 0, "StoreManager's cartRemoveProduct method is not removing products to cart");
        assertEquals(s1.getInventory().getStock(1), 10, "StoreManager's cartRemoveProduct method is not adding products from inventory");
    }

}
