/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dcc171.pkg2017.pkg3.tbr3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import models.Projeto;
import models.Tarefa;
import models.TarefaListModel;
import models.TarefaTableModel;
import percistence.TarefaDAO;

/**
 *
 * @author fhnri
 */
public class janelaTarefa extends javax.swing.JFrame {
    public Projeto projeto=null;
    public Tarefa tarefa=null;
    public JanelaProjetos janelaProj=null;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Creates new form JanelaTarefas
     */
    public janelaTarefa(JanelaProjetos j,Projeto p,Tarefa t) {
        
        janelaProj=j;
        
        initComponents();
        
        projeto=p;
        
        if(t!=null){
        
            tarefa=t;
            descricao.setText(t.getDescricao());
            if(t.getInicio()!=null){
                dataInicio.setText(sdf.format(t.getInicio()));
            }
            if(t.getFim()!=null){
                dataFinal.setText(sdf.format(t.getFim()));
            }
            percentual.setText(Integer.toString(t.getPercentual()));
            diasConclusao.setText(Integer.toString(t.getDiasConclusao()));
            feito.setSelected(t.isFinished());
            populaTarefasAssociada();
        }else{
        
            tarefa=new Tarefa(p);
        
        }
        
    }
    
    public void populaTarefasAssociada(){
        ArrayList<Tarefa> tarefas=TarefaDAO.getInstance().getTarefasAssocidas(this.tarefa);
        lstTarefasAssociadas.setModel(new TarefaListModel(tarefas));
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dataInicio = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        dataFinal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        diasConclusao = new javax.swing.JTextField();
        feito = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        btnSalvaTarefa = new javax.swing.JButton();
        btnCancelaTarefa = new javax.swing.JButton();
        percentual = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTarefasAssociadas = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        btnAddTarefaAssociada = new javax.swing.JButton();

        jLabel3.setText("Data do início");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Descrição da tarefa");

        jLabel2.setText("Data do início");

        jLabel4.setText("Data final");

        jLabel5.setText("Dias para a conclusão");

        feito.setText("Feito");

        jLabel6.setText("Percentual de conclusão");

        btnSalvaTarefa.setText("Salvar");
        btnSalvaTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaTarefaActionPerformed(evt);
            }
        });

        btnCancelaTarefa.setText("Cancelar");
        btnCancelaTarefa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelaTarefaActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(lstTarefasAssociadas);

        jLabel8.setText("Tarefas associadas");

        btnAddTarefaAssociada.setText("Adicionar tarefa");
        btnAddTarefaAssociada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTarefaAssociadaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddTarefaAssociada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelaTarefa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvaTarefa)
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descricao)
                            .addComponent(jLabel1)
                            .addComponent(feito)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(diasConclusao, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 91, Short.MAX_VALUE))
                                    .addComponent(percentual))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(diasConclusao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(percentual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(feito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelaTarefa)
                    .addComponent(btnSalvaTarefa)
                    .addComponent(btnAddTarefaAssociada))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvaTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaTarefaActionPerformed

        tarefa.setDescricao(descricao.getText());
        tarefa.setDiasConclusao(Integer.parseInt(diasConclusao.getText()));
        try {
            System.out.println(dataInicio.getText());
            if(dataInicio.getText().length()>0){
                tarefa.setInicio(sdf.parse(dataInicio.getText()));
            }
            if(dataFinal.getText().length()>0){
                tarefa.setFim(sdf.parse(dataFinal.getText()));
            }
        } catch (ParseException ex) {
            Logger.getLogger(janelaTarefa.class.getName()).log(Level.SEVERE, null, ex);
        }
        tarefa.setPercentual(Integer.parseInt(percentual.getText()));
        tarefa.setStatus(feito.isSelected());
        TarefaDAO.getInstance().Save(tarefa);
        janelaProj.populaTarefas();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnSalvaTarefaActionPerformed

    private void btnCancelaTarefaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelaTarefaActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnCancelaTarefaActionPerformed

    private void btnAddTarefaAssociadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTarefaAssociadaActionPerformed
        JanelaTarefas janela = new JanelaTarefas(tarefa,this);
        janela.setLocationRelativeTo(null);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
    }//GEN-LAST:event_btnAddTarefaAssociadaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTarefaAssociada;
    private javax.swing.JButton btnCancelaTarefa;
    private javax.swing.JButton btnSalvaTarefa;
    private javax.swing.JTextField dataFinal;
    private javax.swing.JTextField dataInicio;
    private javax.swing.JTextField descricao;
    private javax.swing.JTextField diasConclusao;
    private javax.swing.JCheckBox feito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JList<Tarefa> lstTarefasAssociadas;
    private javax.swing.JTextField percentual;
    // End of variables declaration//GEN-END:variables
}
