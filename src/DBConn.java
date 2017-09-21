import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class DBConn {

    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String DB_NAME = "adventurexp";
    private final String USER = "root";
    private final String PASS = "";


    private Connection getConn() {
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    this.URL + this.DB_NAME,
                    this.USER,
                    this.PASS);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void addInstructor(String name) {
        Connection connection = getConn();
        String sql = "INSERT INTO `instructor` (`id`, `name`) VALUES (NULL, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Instructor> getAllInstructors() {
        Connection connection = getConn();
        String sql = "SELECT * FROM `instructor`";
        ArrayList<Instructor> instructorList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                instructorList.add(new Instructor(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
            connection.close();
        } catch (SQLException ex) {

        }

        return instructorList;
    }

    public void addActivity(String name, int price, int age, double height, int instructorId) {
        Connection connection = getConn();
        String sql = "INSERT INTO `activity` (`id`, `name`, `price`, `age`, `height`, `instructor`) VALUES " +
                "(NULL, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, age);
            ps.setDouble(4, height);
            ps.setInt(5, instructorId);
            ps.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateActivity(int id, String name, int price, int age, double height, int instructorId) {
        Connection connection = getConn();
        String sql = "UPDATE `activity` SET `name` = ?, `price` = ?, `age` = ?, `height` = ?, `instructor` = ? " +
                "WHERE `id` = ?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, age);
            ps.setDouble(4, height);
            ps.setInt(5, instructorId);
            ps.setInt(6, id);
            ps.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Activity> getActivities() {
        Connection connection = getConn();
        String sql = "SELECT activity.id,activity.name,activity.price,activity.age,activity.height, " +
                "activity.instructor, instructor.name FROM `activity` " +
                "JOIN `instructor` ON activity.instructor = instructor.id";
        ArrayList<Activity> activityList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                activityList.add(new Activity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5),
                        resultSet.getInt(6)
                ));
            }
            connection.close();
        } catch (SQLException ex) {

        }
        return activityList;
    }

    public ArrayList<Booking> getBookingsByDate (LocalDate date) {

        ArrayList<Booking> bookings = new ArrayList<>();

        if (date == null) {

            return bookings;
        }

        Connection connection = getConn();

        String sql = "SELECT * FROM `booking` " +
                "WHERE booking.date = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(date));

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {

                Calendar calendar = Calendar.getInstance();

                calendar.setTime(resultSet.getTime(3));
                int startTime = calendar.get(Calendar.HOUR_OF_DAY);

                calendar.setTime(resultSet.getTime(4));
                int endTime = calendar.get(Calendar.HOUR_OF_DAY);


                bookings.add(new Booking(
                        resultSet.getInt(1),
                        resultSet.getDate(2).toLocalDate(),
                        startTime,
                        endTime,
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9)
                ));
            }
            connection.close();
        } catch (SQLException ex) {

        }
        return bookings;
    }

    public int addBooking(LocalDate date, int startTime, int endTime, String name,
                          String email, String phoneNo, int partAmount, int activityId) {

        int bookingId = 0;

        Connection connection = getConn();
        String sql = "INSERT INTO `booking` (`date`, `starttime`, `endtime`, `name`, `email`, " +
                "`phonenr`, `participants`, `activity`) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?)";

        Calendar cStart = Calendar.getInstance();
        cStart.set(Calendar.HOUR_OF_DAY, startTime);
        Time timeStart = new Time(cStart.getTimeInMillis());
        Calendar cEnd = Calendar.getInstance();
        cEnd.set(Calendar.HOUR_OF_DAY, endTime);
        Time timeEnd = new Time(cEnd.getTimeInMillis());

        try {

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(date));
            ps.setTime(2, timeStart);
            ps.setTime(3, timeEnd);
            ps.setString(4, name);
            ps.setString(5, email);
            ps.setString(6, phoneNo);
            ps.setInt(7, partAmount);
            ps.setInt(8, activityId);
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookingId;
    }
}
