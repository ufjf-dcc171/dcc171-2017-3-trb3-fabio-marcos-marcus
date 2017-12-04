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
public class UsuarioListModel implements ListModel<Usuario> {
    
    List<Usuario> usuarios;
    private final List<ListDataListener> dataListeners;

    public UsuarioListModel(ArrayList<Usuario> usuarios) {
       this.usuarios=usuarios;
       dataListeners = new ArrayList<>();
    }

    public UsuarioListModel() {
        dataListeners = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return usuarios.size();
    }

    @Override
    public Usuario getElementAt(int index) {
        return usuarios.get(index);
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
