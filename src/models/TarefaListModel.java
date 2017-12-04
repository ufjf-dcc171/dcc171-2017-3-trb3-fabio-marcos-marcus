/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author fhnri
 */
public class TarefaListModel implements ListModel<Tarefa> {
    
    List<Tarefa> tarefas;
    private final List<ListDataListener> dataListeners;

    public TarefaListModel(ArrayList<Tarefa> tarefas) {
       this.tarefas=tarefas;
       dataListeners = new ArrayList<>();
    }

    public TarefaListModel() {
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return tarefas.size();
    }

    @Override
    public Tarefa getElementAt(int index) {
        return tarefas.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        dataListeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        dataListeners.remove(l);
    }

}
