import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DBConnTest {

    DBConn dbConn = new DBConn();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM-yyyy");

    @Test
    public void addInstructor() throws Exception {
        InstructorData instructorData = new InstructorData();
        instructorData.loadList();
        int instructorCount = instructorData.getInstructorList().size();

        dbConn.addInstructor("Kim");
        instructorData = new InstructorData();
        instructorData.loadList();
        assertEquals("Kim", instructorData.getInstructorList().get(instructorCount).getName());

    }

    @Test
    public void updateInstructorWorkdays() throws Exception {

    }

    @Test
    public void getAllInstructors() throws Exception {

    }

    @Test
    public void addActivity() throws Exception {

    }

    @Test
    public void updateActivity() throws Exception {

    }

    @Test
    public void getActivities() throws Exception {

    }

    @Test
    public void getAllBookings() throws Exception {

    }

    @Test
    public void getBookingsByDates() throws Exception {

        LocalDate date1 = LocalDate.parse("27/09-2017", formatter);
        LocalDate date2 = LocalDate.parse("30/09-2017", formatter);

        assertEquals(2, dbConn.getBookingsByDates(date1, date2).size());
    }

    @Test
    public void addBooking() throws Exception {

    }

    @Test
    public void updateBooking() throws Exception {

    }

    @Test
    public void deleteBooking() throws Exception {

    }

    @Test
    public void getSweets() throws Exception {

    }

    @Test
    public void saveSweetsPurchase() throws Exception {

    }

    @Test
    public void addforShop() throws Exception {

    }

    @Test
    public void updateSweets() throws Exception {

    }
}