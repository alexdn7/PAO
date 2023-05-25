package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/onlineStore?user=root";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "ciscosecpa55";

    public DBConnection() {
    }

    public static Connection getDbConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
