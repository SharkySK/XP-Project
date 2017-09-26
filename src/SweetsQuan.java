/**
 * Created by Rasmus on 26-09-2017.
 */
public class SweetsQuan {

    Sweets sweet;
    int quantity;

    public SweetsQuan(Sweets sweet, int quantity) {
        this.sweet = sweet;
        this.quantity = quantity;
    }

    public Sweets getSweet() {
        return sweet;
    }

    public int getQuantity() {
        return quantity;
    }
}
