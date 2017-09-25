import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

/**
 * Created by Jakub Petrík on 25/09/2017.
 */
public class StatsController {

    @FXML
    public DatePicker datePicker;
    @FXML
    public ChoiceBox choiceBox;
    @FXML
    public TableView tableView;

    @FXML
    public void initialize(){
        choiceBox.setItems(FXCollections.observableArrayList("Day", "Week", "Month"));
    }


}
