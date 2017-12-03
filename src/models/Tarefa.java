/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author fhnri
 */
public class Tarefa {
    protected int id;
    protected Projeto projeto;
    protected String descricao;
    protected boolean status;//1 - Aberto, 2- Concluido
    protected Date inicio;
    protected Date fim;
    protected int diasConclusao;
    protected int percentual;
    
    public Tarefa(Projeto proj){
        this.projeto = proj;
    }
    
    public Tarefa(int id, Projeto proj, String descricao, boolean status, Date inicio, Date fim, int diasConclusao, int percentual) {
        this.id = id;
        this.projeto = proj;
        this.descricao = descricao;
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.diasConclusao = diasConclusao;
        this.percentual = percentual;
    }

    public Tarefa(Projeto proj, String descricao, boolean status, Date inicio, Date fim, int diasConclusão, int percentual) {
        this.projeto = proj;
        this.descricao = descricao;
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.diasConclusao = diasConclusao;
        this.percentual = percentual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto proj) {
        this.projeto = proj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinished() {
        return !status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public int getDiasConclusao() {
        return diasConclusao;
    }

    public void setDiasConclusao(int diasConclusao) {
        this.diasConclusao = diasConclusao;
    }

    public int getPercentual() {
        return percentual;
    }

    public void setPercentual(int percentual) {
        this.percentual = percentual;
    }
    
    
    
    
    
}
