package models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TarefaTableModel extends AbstractTableModel {

    private List<Tarefa> tarefas;

    public TarefaTableModel(List<Tarefa> dados) {
        tarefas = dados;
    }

    @Override
    public int getRowCount() {
        return tarefas.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return tarefas.get(rowIndex).isFinished();
            case 1:
                return tarefas.get(rowIndex).getDescricao();
            case 2:
                return tarefas.get(rowIndex).getPercentual();
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Feito";
            case 1:
                return "Descrição";
            case 2:
                return "Percentual";
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void add(Tarefa t) {
       tarefas.add(t);
       //this.fireTableDataChanged();
       this.fireTableRowsInserted(tarefas.size()-1, tarefas.size()-1);
    }
    
    public void remove(int l){
        tarefas.remove(l);
        this.fireTableRowsDeleted(l, l);
    }

    
}
