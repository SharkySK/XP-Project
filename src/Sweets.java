/**
 * Created by Ersan on 9/25/2017.
 */
public class Sweets {
    private int id;
    private String name;
    private String price;

    public Sweets(int id, String name, String price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPrice(){
        return price;
    }

}
