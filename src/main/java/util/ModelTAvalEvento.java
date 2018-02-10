/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Aval_evento;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhonatan
 */
public class ModelTAvalEvento extends AbstractTableModel {

    private final static int COL_SAT = 0;
    private final static int COL_ADEQ_CH = 1;
    private final static int COL_ABR = 2;
    private final static int COL_REL = 3;
    private final static int COL_QUALIDADE = 4;
    private final static int COL_ATUAC_COORD = 5;
    private List<Aval_evento> valores;

    public ModelTAvalEvento(List<Aval_evento> valores) {
        this.valores = valores;
    }

    public List<Aval_evento> getValores() {
        return valores;
    }
    

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aval_evento ac = valores.get(rowIndex);
        if (columnIndex == COL_SAT) {
            return ac.getSatisfacao();
        } else if (rowIndex == COL_ADEQ_CH) {
            return ac.getAdequacao();
        } else if (rowIndex == COL_ABR) {
            return ac.getAbrangencia();
        } else if (columnIndex == COL_REL) {
            return ac.getRelevancia();
        } else if (columnIndex == COL_QUALIDADE) {
            return ac.getQualidade();
        } else if (columnIndex == COL_ATUAC_COORD) {
            return ac.getAtuac_coord();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_SAT:
                coluna = "Satisfação";
                break;
            case COL_ADEQ_CH:
                coluna = "Adeq. CH";
                break;
            case COL_ABR:
                coluna = "Abrangência";
                break;
            case COL_REL:
                coluna = "Relevância";
                break;
            case COL_QUALIDADE:
                coluna = "Qualidade";
                break;
            case COL_ATUAC_COORD:
                coluna = "Atuacão Coord";
                break;

            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_SAT || columnIndex == COL_ADEQ_CH
                || columnIndex == COL_ABR  || columnIndex == COL_REL
                || columnIndex == COL_QUALIDADE || columnIndex == COL_ATUAC_COORD) {
            return String.class;
        }
        return null;
    }

    public Aval_evento get(int row) {
        return valores.get(row);
    }
}
