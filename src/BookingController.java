import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class BookingController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Booking> bookingTableView;
    @FXML
    private ChoiceBox<Activity> activityBox;
    @FXML
    private DatePicker dateField;
    @FXML
    private ChoiceBox<Integer> startField;
    @FXML
    private ChoiceBox<Integer> endField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField partField;

    private BookingData bookingData = new BookingData();
    private ActivityData activityData = new ActivityData();

    private final int OPENTIME = 7;
    private final int CLOSETIME = 20;

    private void setFields(Booking booking) {
        activityBox.setValue(activityData.searchActivity(booking.getActivityId()));
        dateField.setValue(booking.getDate());
        startField.setValue(booking.getStartTime());
        endField.setValue(booking.getEndTime());
        nameField.setText(booking.getName());
        emailField.setText(booking.getEmail());
        phoneField.setText(booking.getPhoneNo());
        partField.setText(Integer.toString(booking.getPartAmount()));
    }

    @FXML
    public void initialize() {
        ObservableList<Integer> bookingTimes = FXCollections.observableArrayList();
        for (int i = OPENTIME; i <= CLOSETIME; i++) {
            bookingTimes.add(i);
        }
        startField.setItems(bookingTimes);
        endField.setItems(bookingTimes);

        loadActivities();

        activityBox.setConverter(new StringConverter<Activity>() {
            @Override
            public String toString(Activity activity) {
                return activity.getName();
            }

            @Override
            public Activity fromString(String string) {
                return null;
            }
        });

        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                searchBooking();
            }
        });
        loadBookingTable();

        bookingTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Booking>() {
            @Override
            public void changed(ObservableValue<? extends Booking> observable, Booking oldValue, Booking newValue) {
                Booking booking = bookingTableView.getSelectionModel().getSelectedItem();
                if (booking != null) {
                    setFields(booking);
                }
            }
        });
    }

    private void loadActivities() {
        activityData.loadList();
        activityBox.setItems(activityData.getActivityList());
    }

    private void searchBooking() {
        bookingTableView.setItems(FXCollections.observableArrayList(bookingData.search(filterField.getText())));
    }


    public void deleteBookings(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel<Booking> row = bookingTableView.getSelectionModel();
        Booking booking = row.getSelectedItem();
        if (booking == null) {
            return;
        }
        DBConn conn = new DBConn();
        conn.deleteBooking(booking.getId());
        loadBookingTable();
    }

    private void loadBookingTable() {
        bookingData.loadBookings();
        bookingTableView.setItems(bookingData.getBookingList());
    }
}
