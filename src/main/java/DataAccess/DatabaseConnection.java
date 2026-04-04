package DataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseConnection {
    private static DatabaseConnection connSingleton;
    private Connection connection;
    private String driver;
    private String url;
    private String host = "localhost";
    private String port = "3306";
    private String db = "sistemapracticasprofesionales";
    private String username = "admin_sistema_practicas";
    private String password = "AdminSPP_19";

    
    private DatabaseConnection() throws SQLException {
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useTimezone=true&serverTimezone=UTC";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connect();
    }
    
    private void connect() throws SQLException{
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
    }
    
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public PreparedStatement prepareStatement(String query) throws SQLException{
        connect();
        return connection.prepareStatement(query);
    }    

    public static DatabaseConnection buildConnection() throws SQLException {
        if(connSingleton == null){
            connSingleton = new DatabaseConnection();
        }
        connSingleton.connect();
        return connSingleton;
    }

}