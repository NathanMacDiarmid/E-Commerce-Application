// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private final int CART_ID;

    public StoreView(StoreManager storeManager, int iD){
        this.storeManager = storeManager;
        this.CART_ID = iD;
    }

    public void GUI() {

        System.out.println("CART ID: " + CART_ID);

        System.out.println("|-------------------------THE CONVENIENT STORE-------------------------|");

        System.out.println("Stock | Product Name | Unit Price | Product ID");

        for (int i = 0; i < storeManager.getInventory().getProduct().size(); i++) {
            String s = String.format(
                    "%d | %s | %.2f | (%d)\n",
                    storeManager.getInventory().getStock().get(i),
                    storeManager.getInventory().getProduct().get(i).getName(),
                    storeManager.getInventory().getProduct().get(i).getPrice(),
                    storeManager.getInventory().getProduct().get(i).getId()
            );
            System.out.println(s);
        }

    }

    public boolean displayGUI() {

        System.out.println("CART ID: " + CART_ID);

        System.out.println("|-------------------------THE CONVENIENT STORE-------------------------|");

        System.out.println("Enter a command...");
        System.out.println("Type 'help' for a list of commands.\n");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (choice.equals("add")) {
            GUI();
            System.out.println("Enter a product id...");
            int product = scanner.nextInt();
            System.out.println("How much...");
            int amount = scanner.nextInt();
            if (storeManager.cartAddProduct(CART_ID, product, amount)) {
                return false;
            }

            System.out.println("Not a valid selection...");
            return false;
        }

        else if (choice.equals("remove")) {
            GUI();
            System.out.println("Enter a product id...");
            int product = scanner.nextInt();
            System.out.println("How much...");
            int amount = scanner.nextInt();
            if (storeManager.cartRemoveProduct(CART_ID, product, amount)) {
                return false;
            }

            System.out.println("Not a valid selection...");
            return false;
        }

        else if (choice.equals("browse")) {
            GUI();
            return false;
        }

        else if (choice.equals("help")) {
            System.out.println("checkout -> ends the current StoreView by checking out and is charged by StoreManager");
            System.out.println("quit -> leave the StoreView and quit the StoreView");
            System.out.println("browse -> shows all the inventory in each StoreView");
            System.out.println("add -> enters the add GUI and you are able to select the product and amount you want to add to your cart");
            System.out.println("remove -> enters the add GUI and you are able to select the product and amount you want to remove from your cart\n");
        }

        else if (choice.equals("checkout")) {
            return true;
        }

        else if (choice.equals("quit")) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        StoreManager sm = new StoreManager();
        StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv2 = new StoreView(sm, sm.assignNewCartID());
        StoreView sv3 = new StoreView(sm, sm.assignNewCartID());
        Product p1 = new Product("apple", 1, 1.0f);
        Product p2 = new Product("banana", 2, 1.0f);
        sm.getInventory().addStock(p1, 30);
        sm.getInventory().addStock(p2, 30);
        StoreView[] users = {sv1, sv2, sv3};
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
    }


}
