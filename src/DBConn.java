import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

    private final String URL = "jdbc:mysql://fypanm.c8w1xrl7tum6.eu-central-1.rds.amazonaws.com:3306/";
    private final String DB_NAME = "motorhome_system";
    private final String USER = "mhadmin";
    private final String PASS = "firstyearkea2k17";


    public Connection getConn() {
        Connection conn = null;
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

}
