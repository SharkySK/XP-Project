import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class ActivityController {
//don't forget to put them in order for tab // to doooo
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

    private InstructorData instructorData = new InstructorData();

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
    private void createActivity(ActionEvent e) {
        String name = nameField.getText();
        Integer price = checkInt(priceField.getText());
        Integer age = checkInt(ageField.getText());
        Double height = checkDouble(heightField.getText());
        if (name != null && !name.trim().isEmpty() &&
                price != null &&
                age != null &&
                height != null &&
                !instructorBox.getSelectionModel().isEmpty()) {
            DBConn dbConn = new DBConn();
            dbConn.addActivity(name, price, age, height, instructorBox.getSelectionModel().getSelectedItem().getId());
        }
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

    private Integer checkInt(String text) {
        if (text.matches("^\\d+$")) {
            return Integer.parseInt(text);
        }
        return null;
    }

    private Double checkDouble(String text) {
        if (text.matches("^\\d+\\.\\d+$")) {
            return Double.parseDouble(text);
        }
        return null;
    }

}
