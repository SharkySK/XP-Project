import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DBConnTest {

    DBConn dbConn = new DBConn();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM-yyyy");

    @Test
    public void getBookingsByDates() throws Exception {

        LocalDate date1 = LocalDate.parse("27/09-2017", formatter);
        LocalDate date2 = LocalDate.parse("30/09-2017", formatter);

        assertEquals(2, dbConn.getBookingsByDates(date1, date2).size());
    }
}