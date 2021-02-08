public class Main {
    public static void main(String[] args) {
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

        testInventory.printProducts();
        testInventory.printStock();

    }
}
