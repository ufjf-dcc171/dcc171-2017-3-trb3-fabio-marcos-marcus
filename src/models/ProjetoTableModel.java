package models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ProjetoTableModel extends AbstractTableModel {

    private List<Projeto> projetos;

    public ProjetoTableModel(List<Projeto> dados) {
        projetos = dados;
    }

    @Override
    public int getRowCount() {
        return projetos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return projetos.get(rowIndex).getId();
            case 1:
                return projetos.get(rowIndex).getDescricao();
            case 2:
                return projetos.get(rowIndex).getTotalTarefas();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Descrição";
            case 2:
                return "Total de tarefas";
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void add(Projeto p) {
       projetos.add(p);
       //this.fireTableDataChanged();
       this.fireTableRowsInserted(projetos.size()-1, projetos.size()-1);
    }
    
    public void remove(int l){
        projetos.remove(l);
        this.fireTableRowsDeleted(l, l);
    }

    
}
