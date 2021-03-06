public class StoreView {
    private StoreManager storeManager;
    private final int CART_ID;

    public StoreView(StoreManager storeManager, int id){
        this.storeManager = storeManager;
        this.CART_ID = id;
    }
}
