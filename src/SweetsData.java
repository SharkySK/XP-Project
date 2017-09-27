import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Ersan on 9/25/2017.
 */
public class SweetsData {

    private ObservableList<Sweets> sweets = FXCollections.observableArrayList();

    public ObservableList<Sweets> getSweets() {
        return sweets;
    }

    public void loadSweets () {
        DBConn dbConn = new DBConn();
        sweets.setAll(dbConn.getSweets());
    }
}
