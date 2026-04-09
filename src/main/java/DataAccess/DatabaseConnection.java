package DataAccess;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
    private static String url;
    private static String user;
    private static String pass;

    static {
        try {
            Properties properties = new Properties();

            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");

            properties.load(input);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

}
