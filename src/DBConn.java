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
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                instructorList.add(new Instructor(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                ));
            }
        } catch (SQLException ex) {

        }

        return instructorList;
    }

}
