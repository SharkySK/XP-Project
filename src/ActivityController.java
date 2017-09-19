import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class ActivityController {

    @FXML
    private TableView activityTable;
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField heightField;
    @FXML
    private ChoiceBox<Instructor> instructorBox;
    @FXML
    private TextField addInstructorField;

    InstructorData instructorData = new InstructorData();

    @FXML
    public void initialize() {
        instructorBox.setConverter(new StringConverter<Instructor>() {
            @Override
            public String toString(Instructor instructor) {
                return instructor.getName();
            }

            @Override
            public Instructor fromString(String string) {
                return null;
            }
        });
        loadInstructors();
    }

    @FXML
    private void createInstructor(ActionEvent e) {
        DBConn dbConn = new DBConn();
        String name = addInstructorField.getText();
        if (name != null && !name.trim().isEmpty()) {
            dbConn.addInstructor(name);
            loadInstructors();
        }
    }

    private void loadInstructors() {
        instructorData.loadList();
        instructorBox.setItems(instructorData.getInstructorList());
    }

}
