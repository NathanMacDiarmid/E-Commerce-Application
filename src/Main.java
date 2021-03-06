import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        StoreManager newManager = new StoreManager();

        Product p1 = new Product("apple", 1, 1.0f);
        Product p2 = new Product("banana", 2, 1.0f);

        newManager.getInventory().addStock(p1, 30);
        newManager.getInventory().addStock(p2, 30);
        newManager.getInventory().printProducts();

        System.out.println(newManager.managerGetStock(1));

        int c1 = newManager.assignNewCartID();

        newManager.cartAddProduct(c1, 1, 15);
        System.out.println(newManager.managerGetStock(1));

        newManager.cartRemoveProduct(c1, 1, 15);
        System.out.println(newManager.managerGetStock(1));

        /*ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> fruit = new ArrayList<>();
        ArrayList<Integer> nonFruit = new ArrayList<>();
        fruit.add(1);
        fruit.add(15);
        nonFruit.add(2);
        nonFruit.add(20);
        list.add(fruit);
        list.add(nonFruit);
        System.out.println(newManager.transactionTotal(list));
        System.out.println(newManager.managerGetStock(1));
        System.out.println(newManager.managerGetStock(2)); */


    }
}
