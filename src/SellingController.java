/**
 * Created by Ersan on 9/25/2017.
 */
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SellingController {


    public TableView<Sweets> productsTbl;
    @FXML
    public TableColumn productClmn;
    @FXML
    public TableColumn unitPriceClmn;
    @FXML
    public TableView<SweetsQuan> selectedTbl;
    @FXML
    public TableColumn selectedProductClmn;
    @FXML
    public TableColumn quanClmn;
    @FXML
    public TableColumn selectedsPriceClmn;
    @FXML
    public TextField idField;
    @FXML
    public TextField phoneField;
    @FXML
    public Button cancelBtn;
    @FXML
    public Button purchaseBtn;

    private ObservableList<Sweets> availProducts;
    private ObservableList<SweetsQuan> selectedProducts;

    @FXML
    public void initialize() {

    }

    @FXML
    public void productsClick(MouseEvent mouseEvent) {

        Sweets sweet = productsTbl.getSelectionModel().getSelectedItem();

        if (sweet == null) {
            return;
        }

        for (SweetsQuan sweetQuan: selectedProducts) {

            if (sweetQuan.getSweet().getId() == sweet.getId()) {

                sweetQuan.addProduct();
                selectedTbl.setItems(selectedProducts);
                return;
            }
        }

        selectedProducts.add(new SweetsQuan(sweet));
        selectedTbl.setItems(selectedProducts);
    }

    @FXML
    public void selectedClick(MouseEvent mouseEvent) {
        
    }
}
