import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookingController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView<Booking> bookingTableView;

    private BookingData bookingData = new BookingData();

    @FXML
    public void initialize() {
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                searchBooking();
            }
        });
        bookingData.loadBookings();
        bookingTableView.setItems(bookingData.getBookingList());
    }

    private void searchBooking() {
        bookingTableView.setItems(FXCollections.observableArrayList(bookingData.search(filterField.getText())));
    }



    public void deleteBookings(ActionEvent actionEvent) {
        TableView.TableViewSelectionModel<Booking> row = bookingTableView.getSelectionModel();
        Booking booking = row.getSelectedItem();
        if(booking==null){
            return;
        }
        DBConn conn = new DBConn();
        conn.deleteBooking(booking.getId());

    }
}
