import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Rasmus on 20-09-2017.
 */
public class BookingData {

    private ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    public ObservableList<Booking> getBookingList() {
        return bookingList;
    }

    public void sortByActivity (int activityId) {

        ArrayList<Booking> sorted = new ArrayList<>();

        for (Booking booking : bookingList) {
            if (booking.getActivityId() == activityId) {
                sorted.add(booking);
            }
        }
        bookingList = FXCollections.observableArrayList(sorted);
    }

    public ArrayList<Booking> search(String name) {
        ArrayList<Booking> arrayList = new ArrayList<>();
        for (Booking booking : bookingList) {
            if (booking.getName().contains(name)) {
                arrayList.add(booking);
            }
        }
        return arrayList;
    }

    public void loadFromDate(LocalDate date) {

        DBConn dbConn = new DBConn();
        bookingList = FXCollections.observableArrayList(dbConn.getBookingsByDate(date));
    }

    public void loadBookings() {
        DBConn dbConn = new DBConn();
        bookingList.setAll(dbConn.getAllBookings());
    }
}
