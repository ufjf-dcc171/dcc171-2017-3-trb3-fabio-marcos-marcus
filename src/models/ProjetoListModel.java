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
public class ProjetoListModel implements ListModel<Projeto> {
    
    List<Projeto> projetos;
    private final List<ListDataListener> dataListeners;

    public ProjetoListModel(ArrayList<Projeto> projetos) {
       this.projetos=projetos;
       dataListeners = new ArrayList<>();
    }

    public ProjetoListModel() {
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return projetos.size();
    }

    @Override
    public Projeto getElementAt(int index) {
        return projetos.get(index);
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
