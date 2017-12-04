/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import percistence.TarefaDAO;

/**
 *
 * @author fhnri
 */
public class Projeto {
    protected int id;
    protected String descricao;

    public Projeto(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Projeto(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    Object getTotalTarefas() {
        return 0;
    }
    
    @Override
    public String toString() {
        return this.descricao+" ("+TarefaDAO.getInstance().totalTarefas(this)+" tarefas)";
    }
    
}
