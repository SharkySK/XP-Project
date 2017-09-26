import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class InstructorController {

    @FXML
    private TextField addInstructorField;

    @FXML
    private void createInstructor(ActionEvent e) {
        DBConn dbConn = new DBConn();
        String name = addInstructorField.getText();
        if (name != null && !name.trim().isEmpty()) {
            dbConn.addInstructor(name);
        }
    }

    @FXML
    private void modifyWorkdays(ActionEvent e) {

    }

}
