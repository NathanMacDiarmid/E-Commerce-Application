package store;
// Nathan MacDiarmid 101098993
// Matthew Belanger 101144323

import java.util.Scanner;

public class StoreView {
    private StoreManager storeManager;
    private final int CART_ID;

    /**
     * Constructor
     * @param storeManager
     * @param iD
     */
    public StoreView(StoreManager storeManager, int iD){
        this.storeManager = storeManager;
        this.CART_ID = iD;
    }

    /**
     * method to print out the current cart id as well as all of the inventory
     */
    public void printGUI() {

        System.out.println("CART ID: " + this.CART_ID);

        System.out.println("|-------------------------THE CONVENIENT STORE-------------------------|");

        System.out.println("Stock | store.Product Name | Unit Price | store.Product ID");

        for (int i = 0; i < storeManager.getInventory().getProduct().size(); i++) {
            String s = String.format(
                    "%d | %s | %.2f | (%d)\n",
                    storeManager.getInventory().getStockList().get(i),
                    storeManager.getInventory().getProduct().get(i).getName(),
                    storeManager.getInventory().getProduct().get(i).getPrice(),
                    storeManager.getInventory().getProduct().get(i).getId()
            );
            System.out.println(s);
        }
    }


    /**
     * this method accepts a command from the user and then completes it then returns a boolean,
     * if it returns false the storeview is still active, if true then the storeview gets deactivated
     * @return boolean
     */
    public boolean displayGUI() {

        printGUI();

        System.out.println("Enter a command...");
        System.out.println("Type 'help' for a list of commands.\n");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

        if (choice.equals("add")) {
            printGUI();
            System.out.println("Enter a product id...");
            int product = scanner.nextInt();
            System.out.println("How much...");
            int amount = scanner.nextInt();
            if (this.storeManager.cartAddProduct(this.CART_ID, product, amount)) {
                return false;
            }

            System.out.println("Not a valid selection...");
            return false;
        }

        else if (choice.equals("remove")) {
            printGUI();
            System.out.println("Enter a product id...");
            int product = scanner.nextInt();
            System.out.println("How much...");
            int amount = scanner.nextInt();
            if (this.storeManager.cartRemoveProduct(this.CART_ID, product, amount)) {
                return false;
            }

            System.out.println("Not a valid selection...");
            return false;
        }

        else if (choice.equals("browse")) {
            printGUI();
            return false;
        }

        else if(choice.equals("view")){
            this.storeManager.printCart(this.CART_ID);
            return false;
        }

        else if (choice.equals("help")) {
            System.out.println("checkout -> ends the current store.StoreView by checking out and is charged by store.StoreManager");
            System.out.println("quit -> leave the store.StoreView and quit the store.StoreView");
            System.out.println("browse -> shows all the inventory in each store.StoreView");
            System.out.println("add -> enters the add GUI and you are able to select the product and amount you want to add to your cart");
            System.out.println("remove -> enters the add GUI and you are able to select the product and amount you want to remove from your cart");
            System.out.println("view -> views the contents in your cart\n");
            return false;
        }

        else if (choice.equals("checkout")) {
            this.storeManager.printCart(this.CART_ID);
            System.out.println("Your transaction total is $" + storeManager.getPrice(this.CART_ID));
            return true;
        }

        else if (choice.equals("quit")) {
            this.storeManager.cartQuit(this.CART_ID);
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