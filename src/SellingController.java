/**
 * Created by Ersan on 9/25/2017.
 */
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SellingController {


    public TableView<Sweets> productsTbl;
    public TableColumn productClmn;
    public TableColumn unitPriceClmn;
    public TableView<SweetsQuan> selectedTbl;
    public TableColumn selectedProductClmn;
    public TableColumn quanClmn;
    public TableColumn selectedsPriceClmn;
    public TextField idField;
    public TextField phoneField;
    public Button cancelBtn;
    public Button purchaseBtn;
}
