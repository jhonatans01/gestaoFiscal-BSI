/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Evento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhonatan
 */
public class ModelTEvento extends AbstractTableModel {

    private final static int COL_TITULO = 0;
    private final static int COL_DATAI = 1;
    private final static int COL_DATAF = 2;
    private final static int COL_LOCAL = 3;
    private List<Evento> valores;

    public ModelTEvento(List<Evento> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento e = valores.get(rowIndex);
        if (columnIndex == COL_TITULO) {
            return e.getTitulo();
        } else if (columnIndex == COL_DATAI) {
            return e.getDataI();
        } else if (columnIndex == COL_DATAF) {
            return e.getDataF();
        } else if (columnIndex == COL_LOCAL) {
            return e.getLocal();
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
            case COL_DATAI:
                coluna = "Data Inicial";
                break;
            case COL_DATAF:
                coluna = "Data Final";
                break;
            case COL_LOCAL:
                coluna = "Local";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_TITULO || columnIndex == COL_DATAI
                || columnIndex == COL_DATAF || columnIndex == COL_LOCAL) {
            return String.class;
        }
        return null;
    }

    public Evento get(int row) {
        return valores.get(row);
    }
}
