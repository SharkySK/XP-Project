import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rasmus on 20-09-2017.
 */
public class CustomerController {

    @FXML
    private TableView<Activity> activityTable;
    @FXML
    public TableColumn activityClmn;
    @FXML
    private TextField priceField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField instructorField;
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
    public Label msgLabel;

    private ActivityData activityData = new ActivityData();

    private final int OPENTIME = 7;
    private final int CLOSETIME = 20;

    @FXML
    public void initialize() {

        activityClmn.setCellValueFactory(new PropertyValueFactory<Activity, String>("Name"));
        loadActivities();

        ObservableList<Integer> bookingTimes = FXCollections.observableArrayList();
        for (int i = OPENTIME; i <= CLOSETIME; i++){

            bookingTimes.add(i);
        }
        startTimeBox.setItems(bookingTimes);
        startTimeBox.setValue(OPENTIME);
        endTimeBox.setItems(bookingTimes);
        endTimeBox.setValue(OPENTIME + 1);
    }

    public void reserveButton(ActionEvent actionEvent) {

        LocalDate date = dateField.getValue();
        int startTime = startTimeBox.getSelectionModel().getSelectedItem();
        int endTime = endTimeBox.getSelectionModel().getSelectedItem();

        if (activityTable.getSelectionModel().getSelectedItem() == null ||
                startTime > endTime || date == null) {

            msgLabel.setText("invalid data input");
            return;
        }
        int activityId = activityTable.getSelectionModel().getSelectedItem().getId();

        if (!checkDate(activityId, date, startTime, endTime)) {

            msgLabel.setText("activity not available");
            return;
        }

        String name = nameField.getText();
        String email = emailField.getText();
        String phoneNo = phoneNoField.getText();
        Integer partAmount = checkInt(participantsField.getText());

        if (name != null && !name.trim().isEmpty() &&
                email != null && !email.trim().isEmpty() &&
                phoneNo != null &&
                partAmount != null) {

            DBConn dbConn = new DBConn();
            int bookingId = dbConn.addBooking(date, startTime, endTime,
                    name, email, phoneNo, partAmount, activityId);

            if (bookingId < 1) {
                msgLabel.setText("booking not created");
            } else {
                msgLabel.setText("booking completed. ID: " + bookingId);
            }

        } else {

            msgLabel.setText("invalid data input");
        }
    }

    private boolean checkDate (int activityId, LocalDate date, int startTime, int endTime) {

        BookingData bookingData = new BookingData();
        bookingData.loadFromDate(date);
        bookingData.sortByActivity(activityId);

        ArrayList<Booking> bookings = new ArrayList<>();
        bookings.addAll(bookingData.getBookingList());

        for (Booking booking : bookings) {

            if ((startTime >= booking.getStartTime() && startTime < booking.getEndTime()) ||
                    (endTime > booking.getStartTime() && endTime <= booking.getEndTime()) ||
                    (startTime <= booking.getStartTime() && endTime >= booking.getEndTime())) {

                return false;
            }
        }

        return true;
    }

    private Integer checkInt(String text) {

        try {
            return Integer.parseInt(text);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public void tableClicked(MouseEvent mouseEvent) {

        Activity activity = activityTable.getSelectionModel().getSelectedItem();

        if (activity != null) {

            priceField.setText(activity.getPrice() + "");
            ageField.setText(activity.getAge() + "");
            heightField.setText(activity.getHeight() + "");
            //instructorField.setText(activity.getPrice() + "");
        }
    }

    private void loadActivities () {

        activityData.loadList();
        activityTable.setItems(activityData.getActivityList());
    }
}
