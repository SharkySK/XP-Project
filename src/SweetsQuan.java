/**
 * Created by Rasmus on 26-09-2017.
 */
public class SweetsQuan {

    private Sweets sweet;
    private int quantity = 0;

    public SweetsQuan(Sweets sweet) {
        this.sweet = sweet;
    }

    public Sweets getSweet() {
        return sweet;
    }

    public int getQuantity() {
        return quantity;
    }

    public int addProduct() {

        quantity++;
        return quantity;
    }

    public int subtractProduct() {

        quantity++;
        return quantity;
    }
}
