package DataAccess;


import Logic.Exceptions.DataIntegrityException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    public void loadConfiguration() {
        try {
            Properties properties = new Properties();

            InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties");
            
            if (input == null) {
                throw new RuntimeException("No se encontro el archivo database.properties");
            }

            properties.load(input);

            URL = properties.getProperty("db.URL");
            USER = properties.getProperty("db.USER");
            PASSWORD = properties.getProperty("db.PASSWORD");

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar la configuración de la base de datos", e);
        }
    }

    public static Connection connect() throws DataIntegrityException{
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DataIntegrityException("Error al conectar con la base de datos", e);
        }
    }

}
