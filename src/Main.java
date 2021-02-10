import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        StoreManager newManager = new StoreManager("Mr. Right");
        Inventory testInventory = new Inventory();
        Product p1 = new Product("apple", 1, 2.0f);
        Product p2 = new Product("banana", 2, 1.5f);
        Product p3 = new Product("orange", 3, 1.0f);

        testInventory.addStock(p1, 5);
        testInventory.addStock(p2, 5);
        testInventory.addStock(p3, 5);
        testInventory.addStock(p1, 5);

        System.out.println(testInventory.removeStock(1, 3));

        System.out.println(testInventory.getStock(1));

        System.out.println(testInventory.getProductInfo(1));

        testInventory.printProducts();
        testInventory.printStock();

        newManager.getInventory().addStock(p1, 30);
        newManager.getInventory().addStock(p2, 15);
        newManager.getInventory().printProducts();
        System.out.println(newManager.managerGetStock(1));
        System.out.println(newManager.managerGetStock(2));

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> fruit = new ArrayList<>();
        ArrayList<Integer> nonFruit = new ArrayList<>();
        ArrayList<Integer> non = new ArrayList<>();

        fruit.add(1);
        fruit.add(15);

        nonFruit.add(2);
        nonFruit.add(20);

        list.add(fruit);
        list.add(nonFruit);

        System.out.println(newManager.transactionTotal(list));
        System.out.println(newManager.managerGetStock(1));
        System.out.println(newManager.managerGetStock(2));


    }
}
