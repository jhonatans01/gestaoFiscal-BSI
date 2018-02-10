/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entity.Apoio_logistico;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhonatan
 */
public class ModelTApoioLog extends AbstractTableModel {

    private final static int COL_QUAL_SERV = 0;
    private final static int COL_ADEQ_IF = 1;
    private final static int COL_PERC = 2;
    private final static int COL_CRITICAS = 3;
    private List<Apoio_logistico> valores;

    public ModelTApoioLog(List<Apoio_logistico> valores) {
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
        Apoio_logistico ae = valores.get(rowIndex);
        if (columnIndex == COL_QUAL_SERV) {
            return ae.getQualidade();
        } else if (columnIndex == COL_CRITICAS) {
            return ae.getCriticas_sug();
        } else if (columnIndex == COL_ADEQ_IF) {
            return ae.getAdequacao();
        } else if (columnIndex == COL_PERC) {
            return ae.getPercentual_obj();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_QUAL_SERV:
                coluna = "Qualidade do serviço";
                break;
            case COL_PERC:
                coluna = "Perc. Objetivo";
                break;
            case COL_CRITICAS:
                coluna = "Críticas e Sug.";
                break;
            case COL_ADEQ_IF:
                coluna = "Adequação das Inst. Físicas";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CRITICAS || columnIndex == COL_QUAL_SERV
                || columnIndex == COL_PERC || columnIndex == COL_ADEQ_IF) {
            return String.class;
        }
        return null;
    }

    public Apoio_logistico get(int row) {
        return valores.get(row);
    }
}
