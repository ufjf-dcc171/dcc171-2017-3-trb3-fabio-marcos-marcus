package percistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLocator {
    
    private static DatabaseLocator instance = new DatabaseLocator();
    
    private DatabaseLocator(){}
    
    public static DatabaseLocator getInstance(){
        return instance;
    }
    
    public Connection getConnection(){
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/dcc171-trb3";
            Connection conn = DriverManager.getConnection(driverURL,"usuario","senha");
            
            return conn;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
