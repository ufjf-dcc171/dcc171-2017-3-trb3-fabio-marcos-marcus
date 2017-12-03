/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

/**
 *
 * @author fhnri
 */
public class UsuarioDAO {
     private static UsuarioDAO instance = new UsuarioDAO();
    
    private UsuarioDAO(){}
    
    public static UsuarioDAO getInstance(){
        return instance;
    }
    
    public void Save(Usuario usuario){
        
        Connection conn = null;
        
        PreparedStatement  stmt = null;
        
        conn = DatabaseLocator.getInstance().getConnection();
           
        try {

            if(usuario.getId()>0){

                stmt = conn.prepareStatement("UPDATE usuario SET nome=? WHERE idUsuario=?");
                stmt.setString(1, usuario.getNome());
                stmt.setInt(2, usuario.getId());
                stmt.executeUpdate();
                
            }else{
                
                stmt = conn.prepareStatement("INSERT INTO usuario(nome) values (?)",Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, usuario.getNome());
                stmt.executeUpdate();
                
                ResultSet genKeys = stmt.getGeneratedKeys();
                genKeys.next();

                int idUsuario = genKeys.getInt(1);            
                usuario.setId(idUsuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
    }
    
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
}
