package store;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        StoreManager newManager = new StoreManager();

        Product p1 = new Product("apple", 1, 1.0f);
        Product p2 = new Product("banana", 2, 1.0f);

        newManager.getInventory().addStock(p1, 30);
        newManager.getInventory().addStock(p2, 30);
        newManager.getInventory().printProducts();

        //System.out.println(newManager.managerGetStock(1));

        //int c1 = newManager.assignNewCartID();

        //newManager.cartAddProduct(c1, 1, 15);
       // System.out.println(newManager.managerGetStock(1));

        //newManager.cartRemoveProduct(c1, 1, 15);
        //System.out.println(newManager.managerGetStock(1));

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> fruit = new ArrayList<>();
        ArrayList<Integer> nonFruit = new ArrayList<>();
        fruit.add(1);
        fruit.add(15);
        nonFruit.add(2);
        nonFruit.add(20);
        list.add(fruit);
        list.add(nonFruit);
        //System.out.println(newManager.transactionTotal(list));
        //System.out.println(newManager.managerGetStock(1));
        //System.out.println(newManager.managerGetStock(2));

        //store.StoreView sv1 = new store.StoreView(newManager, newManager.assignNewCartID());
        //sv1.displayGUI();

        //store.StoreManager sm = new store.StoreManager();
        /*store.StoreView sv1 = new store.StoreView(newManager, newManager.assignNewCartID());
        store.StoreView sv2 = new store.StoreView(newManager, newManager.assignNewCartID());
        store.StoreView sv3 = new store.StoreView(newManager, newManager.assignNewCartID());
        store.StoreView[] users = {sv1, sv2, sv3};
        int activeSV = users.length;
        Scanner sc = new Scanner(System.in);
        while (activeSV > 0) {
            System.out.print("CHOOSE YOUR STOREVIEW >>> ");
            int choice = sc.nextInt();
            if (choice < users.length && choice >= 0) {
                if (users[choice] != null) {
                    String chooseAnother = "";
                    while (!chooseAnother.equals("y") && !chooseAnother.equals("Y")) {
                        // this implementation of displayGUI waits for input and displays the page
                        // corresponding to the user's input. it does this once, and then returns
                        // true if the user entered 'checkout' or 'quit'.
                        if (users[choice].displayGUI()) {
                            users[choice] = null;
                            activeSV--;
                            break;
                        }
                        System.out.print("GO TO ANOTHER STOREVIEW? (y) >>> ");
                        chooseAnother = sc.next();
                    }
                } else {
                    System.out.println("MAIN > ERROR > BAD CHOICE\nTHAT STOREVIEW WAS DEACTIVATED");
                }
            } else {
                System.out.println(
                        String.format("MAIN > ERROR > BAD CHOICE\nPLEASE CHOOSE IN RANGE [%d, %d]",
                                0, users.length - 1)
                );
            }
        }
        System.out.println("ALL STOREVIEWS DEACTIVATED");

         */

    }
}