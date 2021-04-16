// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

package storetest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import myStore.Product;
import myStore.StoreManager;

public class StoreManagerTest {
    private static StoreManager s1;
    private static Product p1;
    private static Product p2;
    private static Product p3;
    private static Product p4;
    private static int c1;
    private static int c2;
    private static int c3;

    /**
     * Initialize attributes
     */
    @BeforeAll
    public static void init(){
        s1 = new StoreManager();
        p1 = new Product("Apples", 1, 2.0f);
        p2 = new Product("Bananas", 2, 3.0f);
        p3 = new Product("Oranges", 3, 1.0f);
        p4 = new Product("Pears", 4, 4.0f);
        c1 = s1.assignNewCartID();
        c2 = s1.assignNewCartID();
        c3 = s1.assignNewCartID();
    }

    /**
     * Test StoreManager's assignNewCartID to see if it's assigning the correct value to new carts
     */
    @Test
    public void testAssignNewCartID(){
        assertEquals(c1, 0, "StoreManager's assignNewCartID method is not working");
        assertEquals(c2, 1, "StoreManager's assignNewCartID method is not working");
        assertEquals(c3, 2, "StoreManager's assignNewCartID method is not working");
    }

    /**
     * Test StoreManager's cartAddProduct method by checking if it returns the correct value and if it adds the
     * stock to the correct cart while also removing the correct amount of stock from the StoreManager's inventory
     */
    @Test
    public void testCartAddProduct(){
        assertEquals(s1.cartAddProduct(c1, p1, 10), false, "StoreManager's cartAddProduct method is not returning proper value");
        s1.getInventory().addProductQuantity(p1, 10);
        assertEquals(s1.cartAddProduct(c1, p1, 10), true, "StoreManager's cartAddProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getProductQuantity(p1), 10, "StoreManager's cartAddProduct method is not adding products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p1), 0, "StoreManager's cartAddProduct method is not removing products from inventory");

        s1.getInventory().addProductQuantity(p2, 30);
        assertEquals(s1.cartAddProduct(c1, p2, 20), true, "StoreManager's cartAddProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getProductQuantity(p2), 20, "StoreManager's cartAddProduct method is not adding products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p2), 10, "StoreManager's cartAddProduct method is not removing products from inventory");

        assertEquals(s1.cartAddProduct(c1, p2, 0), true, "StoreManager's cartAddProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getProductQuantity(p2), 20, "StoreManager's cartAddProduct method is not adding products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p2), 10, "StoreManager's cartAddProduct method is not removing products from inventory");

        assertEquals(s1.cartAddProduct(c1, p2, -10), false, "StoreManager's cartAddProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c1).getProductQuantity(p2), 20, "StoreManager's cartAddProduct method is not adding products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p2), 10, "StoreManager's cartAddProduct method is not removing products from inventory");
    }

    /**
     * Test StoreManager's getPrice method to see if it is returning the correct amount
     */
    @Test
    public void testGetPrice(){
        s1.getInventory().addProductQuantity(p1, 20);
        s1.getInventory().addProductQuantity(p2, 5);
        s1.cartAddProduct(c2, p1, 20);
        s1.cartAddProduct(c2, p2, 5);
        assertEquals(s1.getPrice(c2), 55.0f, "StoreManager's getPrice method is not returning proper price");
    }

    /**
     * Test StoreManager's cartRemoveProduct method by checking if it returns the correct value and if it removes the
     * stock from the correct cart while also adding the correct amount of stock to the StoreManager's inventory
     */
    @Test
    public void testCartRemoveProducts(){
        assertEquals(s1.cartRemoveProduct(c3, p3, 10), false, "StoreManager's cartRemoveProduct method is not returning proper value");
        s1.getInventory().addProductQuantity(p3, 10);
        s1.cartAddProduct(c3, p3, 10);
        assertEquals(s1.cartRemoveProduct(c3, p3, 10), true, "StoreManager's cartRemoveProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c3).getProductQuantity(p3), 0, "StoreManager's cartRemoveProduct method is not removing products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p3), 10, "StoreManager's cartRemoveProduct method is not adding products from inventory");

        s1.getInventory().addProductQuantity(p4, 20);
        s1.cartAddProduct(c3, p4, 20);
        assertEquals(s1.cartRemoveProduct(c3, p4, 5), true, "StoreManager's cartRemoveProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c3).getProductQuantity(p4), 15, "StoreManager's cartRemoveProduct method is not removing products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p4), 5, "StoreManager's cartRemoveProduct method is not adding products from inventory");

        assertEquals(s1.cartRemoveProduct(c3, p4, 0), true, "StoreManager's cartRemoveProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c3).getProductQuantity(p4), 15, "StoreManager's cartRemoveProduct method is not removing products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p4), 5, "StoreManager's cartRemoveProduct method is not adding products from inventory");

        assertEquals(s1.cartRemoveProduct(c3, p4, -10), false, "StoreManager's cartRemoveProduct method is not returning proper value");
        assertEquals(s1.getShoppingCarts().get(c3).getProductQuantity(p4), 15, "StoreManager's cartRemoveProduct method is not removing products to cart");
        assertEquals(s1.getInventory().getProductQuantity(p4), 5, "StoreManager's cartRemoveProduct method is not adding products from inventory");


    }

}
