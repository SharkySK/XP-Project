import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Activity> getActivities() {
        Connection connection = getConn();
        String sql = "SELECT activity.id,activity.name,activity.price,activity.age,activity.height,instructor.name " +
                "FROM `activity` JOIN `instructor` ON activity.instructor = instructor.id";
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
                        resultSet.getString(6)
                ));
            }
            connection.close();
        } catch (SQLException ex) {

        }
        return activityList;
    }

}
