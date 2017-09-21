import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class ActivityController {
//don't forget to put them in order for tab // to doooo
    @FXML
    private TableView<Activity> activityTable;
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
    private ActivityData activityData = new ActivityData();

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
        loadActivities();

        activityTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Activity>() {
            @Override
            public void changed(ObservableValue<? extends Activity> observable, Activity oldValue, Activity newValue) {
                Activity activity = activityTable.getSelectionModel().getSelectedItem();
                if (activity != null) {
                    setFields(activity);
                }
            }
        });
    }

    @FXML
    private void createActivity(ActionEvent e) {
        Activity activity = checkActivities(null);
        if (activity != null) {
            DBConn dbConn = new DBConn();
            dbConn.addActivity(activity.getName(), activity.getPrice(), activity.getAge(),
                    activity.getHeight(), activity.getInstructorId());
            loadActivities();
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

    @FXML
    private void saveChanges(ActionEvent e) {
        int activityId = activityTable.getSelectionModel().getSelectedItem().getId();
        Activity activity = checkActivities(activityId);
        if (activity != null) {
            DBConn dbConn = new DBConn();
            dbConn.updateActivity(activity.getId(), activity.getName(), activity.getPrice(),
                    activity.getAge(), activity.getHeight(), activity.getInstructorId());
            loadActivities();
        }
    }

    @FXML
    private void clearFields(ActionEvent e) {
        nameField.setText("");
        priceField.setText("");
        ageField.setText("");
        heightField.setText("");
        instructorBox.getSelectionModel().select(null);
        addInstructorField.setText("");
    }

    private Activity checkActivities(Integer activityId) {
        String name = nameField.getText();
        Integer price = checkInt(priceField.getText());
        Integer age = checkInt(ageField.getText());
        Double height = checkDouble(heightField.getText());
        if (name != null && !name.trim().isEmpty() &&
                price != null &&
                age != null &&
                height != null &&
                !instructorBox.getSelectionModel().isEmpty()) {
            return new Activity(activityId == null ? 0 : activityId, name, price, age, height,
                    instructorBox.getSelectionModel().getSelectedItem().getId());
        }
        return null;
    }

    private void setFields(Activity activity) {
        nameField.setText(activity.getName());
        priceField.setText(String.valueOf(activity.getPrice()));
        ageField.setText(String.valueOf(activity.getAge()));
        heightField.setText(String.valueOf(activity.getHeight()));
        instructorBox.getSelectionModel().select(instructorData.searchInstructor(activity.getInstructorId()));

    }

    private void loadInstructors() {
        instructorData.loadList();
        instructorBox.setItems(instructorData.getInstructorList());
    }

    private void loadActivities() {
        activityData.loadList();
        activityTable.setItems(activityData.getActivityList());
    }

// make a class static with checkint and checkdouble
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
