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
import models.Projeto;
import models.Tarefa;

/**
 *
 * @author fhnri
 */
public class TarefaDAO {
    
    private static TarefaDAO instance = new TarefaDAO();
    
    private TarefaDAO(){}
    
    public static TarefaDAO getInstance(){
        return instance;
    }
    
    public void Save(Tarefa tarefa){
        
        Connection conn = null;
        
        PreparedStatement  stmt = null;
        
        conn = DatabaseLocator.getInstance().getConnection();
           
        try {

            if(tarefa.getId()>0){

                stmt = conn.prepareStatement("UPDATE tarefa SET idProjeto=?,descricao=?,datainicio=?,datafinal=?,diasConclusao=?,percentual=?,status=? WHERE idTarefa=?");
                stmt.setInt(1, tarefa.getProjeto().getId());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setDate(3, (Date) tarefa.getInicio());
                stmt.setDate(4, (Date) tarefa.getFim());
                stmt.setInt(5, tarefa.getDiasConclusao());
                stmt.setInt(6, tarefa.getPercentual());
                stmt.setInt(7, tarefa.getId());
                stmt.setBoolean(8, tarefa.isFinished());
                stmt.executeUpdate();
                
                
            }else{
                
                stmt = conn.prepareStatement("INSERT INTO tarefa (idProjeto,descricao,datainicio,datafinal,diasConclusao,percentual,status) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, tarefa.getProjeto().getId());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setDate(3, (Date) tarefa.getInicio());
                stmt.setDate(4, (Date) tarefa.getFim());
                stmt.setInt(5, tarefa.getDiasConclusao());
                stmt.setInt(6, tarefa.getPercentual());
                stmt.setBoolean(7, tarefa.isFinished());
                stmt.executeUpdate();
                
                ResultSet genKeys = stmt.getGeneratedKeys();
                genKeys.next();

                int idTarefa = genKeys.getInt(1);            
                tarefa.setId(idTarefa);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
    }
    
    public Tarefa getTarefa(int id,Projeto proj){
        Connection conn = null;
        PreparedStatement  stmt = null;
        Tarefa tarefa=null;
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM tarefa WHERE idTarefa=? LIMIT 1");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                tarefa = new Tarefa(rs.getInt("idTarefa"), proj, rs.getString("descricao"), rs.getBoolean("status"), rs.getDate("datainicio"), rs.getDate("datafinal"), rs.getInt("diasconclusao"), rs.getInt("percentual"));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return tarefa;
    }
    
    public ArrayList<Tarefa> getTarefas(Projeto proj){
        Connection conn = null;
        PreparedStatement  stmt = null;
        ArrayList<Tarefa> tarefas=new ArrayList<>();
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM tarefa WHERE idProjeto=?");
            stmt.setInt(1, proj.getId());
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                tarefas.add(new Tarefa(rs.getInt("idTarefa"), proj, rs.getString("descricao"), rs.getBoolean("status"), rs.getDate("datainicio"), rs.getDate("datafinal"), rs.getInt("diasconclusao"), rs.getInt("percentual")));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return tarefas;
    }
    
    
    
    
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }
}
