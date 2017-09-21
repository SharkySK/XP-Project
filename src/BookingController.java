import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * Created by Jakub Petrík on 21/09/2017.
 */
public class BookingController {
    @FXML
    private TextField filterField;
    @FXML
    private TableView bookingTableView;

    private BookingData bookingData;
    private Booking booking;

    @FXML
    public void initialize() {
        bookingTableView.setItems(bookingData.getBookingList());
    }

    private void searchBooking() {


        FilteredList<Booking> filteredData = new FilteredList(bookingData.getBookingList(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name field in your object with filter.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(booking.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches first name.

                } else if (String.valueOf(booking.getEmail()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Booking> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(bookingTableView.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        bookingTableView.setItems(sortedData);
    }
}
