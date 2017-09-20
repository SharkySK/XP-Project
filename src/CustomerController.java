import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Rasmus on 20-09-2017.
 */
public class CustomerController {

    @FXML
    private TableView activityTable;
    @FXML
    private DatePicker dateField;
    @FXML
    private ChoiceBox<Integer> startTimeBox;
    @FXML
    private ChoiceBox<Integer> endTimeBox;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneNoField;
    @FXML
    private TextField participantsField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField instructorField;

    @FXML
    public void initialize() {

        ObservableList<Integer> bookingTimes = FXCollections.observableArrayList();
        for (int i = 7; i <= 20; i++){

            bookingTimes.add(i);
        }
        startTimeBox.setItems(bookingTimes);
        endTimeBox.setItems(bookingTimes);
    }
}
