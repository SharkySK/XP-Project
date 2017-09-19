import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {

    private final String URL = "";
    private final String DB_NAME = "";
    private final String USER = "";
    private final String PASS = "";


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
