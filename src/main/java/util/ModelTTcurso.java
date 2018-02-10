/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Tema_curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhonatan
 */
public class ModelTTcurso extends AbstractTableModel {

    private final static int COL_TITULO = 0;
    private List<Tema_curso> valores;

    public ModelTTcurso(List<Tema_curso> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tema_curso tc = valores.get(rowIndex);
        if (columnIndex == COL_TITULO) {
            return tc.getTitulo();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_TITULO:
                coluna = "Título";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_TITULO) {
            return String.class;
        }
        return null;
    }

    public Tema_curso get(int row) {
        return valores.get(row);
    }
}
