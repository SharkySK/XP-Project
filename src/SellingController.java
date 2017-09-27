/**
 * Created by Ersan on 9/25/2017.
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<Sweets> availProducts = FXCollections.observableArrayList();
    private ObservableList<SweetsQuan> selectedProducts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        productClmn.setCellValueFactory(new PropertyValueFactory<Activity, String>("Name"));
        unitPriceClmn.setCellValueFactory(new PropertyValueFactory<Activity, Double>("Price"));

        selectedProductClmn.setCellValueFactory(new PropertyValueFactory<Activity, String>("Name"));
        quanClmn.setCellValueFactory(new PropertyValueFactory<Activity, Integer>("Quantity"));
        selectedsPriceClmn.setCellValueFactory(new PropertyValueFactory<Activity, Double>("Price"));

        SweetsData sweetsData = new SweetsData();
        sweetsData.loadSweets();
        availProducts = sweetsData.getSweets();
        productsTbl.setItems(availProducts);
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
                updateSelectedTable();
                return;
            }
        }

        selectedProducts.add(new SweetsQuan(sweet));
        updateSelectedTable();
    }

    @FXML
    public void selectedClick(MouseEvent mouseEvent) {

        SweetsQuan quan = selectedTbl.getSelectionModel().getSelectedItem();

        if (quan == null) {
            return;
        }

        if (quan.subtractProduct() <= 0) {

            int loops = selectedProducts.size();
            for (int i = 0; i < loops; i++) {

                if (selectedProducts.get(i) == quan) {

                    selectedProducts.remove(i);
                    break;
                }
            }
        }
        updateSelectedTable();
    }

    private void updateSelectedTable() {
        selectedTbl.setItems(selectedProducts);
        selectedTbl.refresh();
    }
}
