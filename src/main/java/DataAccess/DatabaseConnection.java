package DataAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    
    static String url="jdbc:mysql://localhost:3306/sistemapracticasprofesionales";
    static String user="admin_sistema_practicas";
    static String pass="AdminSPP_19";
            
    public static Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return connection; 
        
    }
    
}
