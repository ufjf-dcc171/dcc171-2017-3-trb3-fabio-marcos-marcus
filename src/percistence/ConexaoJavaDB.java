/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 11944413600
 */
public class ConexaoJavaDB {
    
    private static Connection instance = null;
    
    public static Connection getConnection() throws Exception{
        
        if(instance==null){
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String driverURL = "jdbc:derby://localhost:1527/dcc171-trb3";
            instance = DriverManager.getConnection(driverURL,"usuario","senha");
        }
        return instance;
    } 
}
