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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Projeto;
import models.Tarefa;
import models.Usuario;

/**
 *
 * @author fhnri
 */
public class TarefaDAO {
    
    private static TarefaDAO instance = new TarefaDAO();
    public DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    
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
                System.out.println(tarefa.getInicio().getTime());
                stmt = conn.prepareStatement("UPDATE tarefa SET idProjeto=?,descricao=?,datainicio=?,datafinal=?,diasConclusao=?,percentual=?,status=? WHERE idTarefa=?");
                stmt.setInt(1, tarefa.getProjeto().getId());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setDate(3, new java.sql.Date(tarefa.getInicio().getTime()));
                stmt.setDate(4, new java.sql.Date(tarefa.getFim().getTime()));
                stmt.setInt(5, tarefa.getDiasConclusao());
                stmt.setInt(6, tarefa.getPercentual());
                stmt.setBoolean(7, tarefa.isFinished());
                stmt.setInt(8, tarefa.getId());
                stmt.executeUpdate();
                stmt = conn.prepareStatement("UPDATE tarefaassociada SET status=? WHERE idtarefaassociada=?");
                stmt.setBoolean(1, !tarefa.isFinished());
                stmt.setInt(2, tarefa.getId());
                stmt.executeUpdate();
                
            }else{
                
                stmt = conn.prepareStatement("INSERT INTO tarefa (idProjeto,descricao,datainicio,datafinal,diasConclusao,percentual,status) values (?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                stmt.setInt(1, tarefa.getProjeto().getId());
                stmt.setString(2, tarefa.getDescricao());
                stmt.setDate(3, new java.sql.Date(tarefa.getInicio().getTime()));
                stmt.setDate(4, new java.sql.Date(tarefa.getFim().getTime()));
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
    
    public ArrayList<Tarefa> getTarefas(Projeto proj,String view,Boolean possiveisConcluir){
        Connection conn = null;
        PreparedStatement  stmt = null;
        ArrayList<Tarefa> tarefas=new ArrayList<>();
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            if(view=="Todos" || view==""){
            
                stmt = conn.prepareStatement("SELECT * FROM tarefa WHERE idProjeto=? ORDER BY idTarefa");
                stmt.setInt(1, proj.getId());
                
            }else{
                boolean status=view=="Feitos"?false:true;
                stmt = conn.prepareStatement("SELECT * FROM tarefa WHERE idProjeto=? AND status=? ORDER BY idTarefa");
                stmt.setInt(1, proj.getId());
                stmt.setBoolean(2, status);
                
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Tarefa tarefa = new Tarefa(rs.getInt("idTarefa"), proj, rs.getString("descricao"), rs.getBoolean("status"), rs.getDate("datainicio"), rs.getDate("datafinal"), rs.getInt("diasconclusao"), rs.getInt("percentual"));
                
                if(!possiveisConcluir || podeConcluir(tarefa)){
                    
                    tarefas.add(tarefa);
                }
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return tarefas;
    }
    
    public int totalTarefas(Projeto p){
        Connection conn = null;
        PreparedStatement  stmt = null;
        int total=0;
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT COUNT(*) As Total FROM tarefa WHERE idProjeto=?");
            
            stmt.setInt(1, p.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                total = rs.getInt("Total");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        return total;
    }
    
    public ArrayList<Tarefa> getTarefasAssocidas(Tarefa t){
        Connection conn = null;
        PreparedStatement  stmt = null;
        ArrayList<Tarefa> tarefas=new ArrayList<>();
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT tarefa.* FROM tarefaassociada LEFT JOIN tarefa ON tarefa.idTarefa=tarefaAssociada.idtarefaassociada WHERE tarefaAssociada.idtarefa=?");
            stmt.setInt(1, t.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                tarefas.add(new Tarefa(rs.getInt("idTarefa"), t.getProjeto(), rs.getString("descricao"), rs.getBoolean("status"), rs.getDate("datainicio"), rs.getDate("datafinal"), rs.getInt("diasconclusao"), rs.getInt("percentual")));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return tarefas;
    }
    
    public void adicionaTarefaAssociada(Tarefa t1,Tarefa t2){
        Connection conn = null;
        PreparedStatement  stmt = null;
        conn = DatabaseLocator.getInstance().getConnection();
        try {
            stmt = conn.prepareStatement("INSERT INTO tarefaassociada(idtarefa,idtarefaassociada,status) values(?,?,?)");
            stmt.setInt(1, t1.getId());
            stmt.setInt(2, t2.getId());
            stmt.setBoolean(3, t2.isFinished());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public boolean podeConcluir(Tarefa t){
        Connection conn = null;
        PreparedStatement  stmt = null;
        boolean podeConcluir=true;
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();
            
            stmt = conn.prepareStatement("SELECT COUNT(*) As Total FROM tarefaassociada WHERE idTarefa=? AND status=false");
            System.out.println(t.getId());
            stmt.setInt(1, t.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int total = rs.getInt("Total");
                
                System.out.println(total);
                
                if(total>0){
                    podeConcluir=false;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        return podeConcluir;
    }
    
    public void closeResources(Connection conn, Statement st){
        try {
            if(st!=null) st.close();
            if(conn!=null) conn.close();

        } catch(SQLException e) {

        }
    }

    public void adicionaUsuarioTarefa(Tarefa tarefa,Usuario usuario) {
        Connection conn = null;
        PreparedStatement  stmt = null;
        conn = DatabaseLocator.getInstance().getConnection();
        try {
            stmt = conn.prepareStatement("INSERT INTO usuariotarefa(idtarefa,idusuario) values(?,?)");
            stmt.setInt(1, tarefa.getId());
            stmt.setInt(2, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
    }

    public ArrayList<Usuario> getUsuariosTarefas(Tarefa tarefa) {
        Connection conn = null;
        PreparedStatement  stmt = null;
        ArrayList<Usuario> usuarios=new ArrayList<>();
        
        try {
            
            conn = DatabaseLocator.getInstance().getConnection();

            stmt = conn.prepareStatement("SELECT usuario.* FROM usuariotarefa LEFT JOIN usuario ON usuario.idUsuario=usuariotarefa.idUsuario WHERE usuariotarefa.idtarefa=?");
            stmt.setInt(1, tarefa.getId());
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                
                usuarios.add(new Usuario(rs.getInt("idUsuario"), rs.getString("Nome")));
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TarefaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            
            closeResources(conn, stmt);
            
        }
        
        return usuarios;
    }
}
