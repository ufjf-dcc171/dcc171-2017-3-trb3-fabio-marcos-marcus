/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package percistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Projeto;

/**
 *
 * @author fhnri
 */
public class ProjetoDAO {
    
    private static ProjetoDAO instance = new ProjetoDAO();
    
    private ProjetoDAO(){}
    
    public static ProjetoDAO getInstance(){
        return instance;
    }
    
    public void Save(Projeto proj){
        
        Connection conn = null;
        
        PreparedStatement  stmt = null;
        
        conn = DatabaseLocator.getInstance().getConnection();
           
        try {

            if(proj.getId()>0){

                stmt = conn.prepareStatement("UPDATE projeto SET descricao=? WHERE idProjeto=?");
                stmt.setString(1, proj.getDescricao());
                stmt.setInt(2, proj.getId());
                stmt.executeUpdate();
                
                
            }else{
                
                stmt = conn.prepareStatement("INSERT INTO projeto (descricao) values (?)",Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, proj.getDescricao());
                stmt.executeUpdate();
                
                ResultSet genKeys = stmt.getGeneratedKeys();
                genKeys.next();

                int idProjeto = genKeys.getInt(1);            
                proj.setId(idProjeto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
    }
    
    public Projeto getProjeto(int id){
        
        Connection conn = null;
        PreparedStatement  stmt = null;
        Projeto proj=null;
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM projeto WHERE idProjeto=? LIMIT 1");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                proj = new Projeto(rs.getInt("idProjeto"), rs.getString("descricao"));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return proj;

    }
    
    public ArrayList<Projeto> getProjetos(){
        Connection conn = null;
        PreparedStatement  stmt = null;
        ArrayList<Projeto> projetos=new ArrayList();
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM projeto");
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                projetos.add(new Projeto(rs.getInt("idProjeto"), rs.getString("descricao")));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ProjetoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return projetos;
    }
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
}
