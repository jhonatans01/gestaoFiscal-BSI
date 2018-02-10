/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import crud.Tema_cursoCRUD;
import entity.Aval_curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jhonatan
 */
public class ModelTAvalCurso extends AbstractTableModel {

    private final static int COL_TEMA = 0;
    private final static int COL_PONTUALIDADE = 1;
    private final static int COL_DOMINIO_ASSUNTO = 2;
    private final static int COL_CLAREZA = 3;
    private final static int COL_DISTR_CH = 4;
    private final static int COL_MOTIV_GRUPO = 5;
    private final static int COL_ESCL_DUVIDAS = 6;
    private List<Aval_curso> valores;
    private final Tema_cursoCRUD crud = new Tema_cursoCRUD();

    public ModelTAvalCurso(List<Aval_curso> valores) {
        this.valores = valores;
    }

    @Override
    public int getRowCount() {
        return valores.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aval_curso ac = valores.get(rowIndex);
        if (columnIndex == COL_TEMA) {
            return crud.obter(ac.getTema_curso_id()).getTitulo();
        } else if (columnIndex == COL_CLAREZA) {
            return ac.getClareza();
        } else if (columnIndex == COL_DISTR_CH) {
            return ac.getDistr_ch();
        } else if (columnIndex == COL_DOMINIO_ASSUNTO) {
            return ac.getDominio_assunto();
        } else if (columnIndex == COL_ESCL_DUVIDAS) {
            return ac.getEscl_duvidas();
        } else if (columnIndex == COL_MOTIV_GRUPO) {
            return ac.getMotiv_grupo();
        } else if (rowIndex == COL_PONTUALIDADE) {
            return ac.getPontualidade();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        String coluna = "";
        switch (column) {
            case COL_TEMA:
                coluna = "Tema";
                break;
            case COL_DISTR_CH:
                coluna = "Distr. CH";
                break;
            case COL_DOMINIO_ASSUNTO:
                coluna = "Dominio ass.";
                break;
            case COL_ESCL_DUVIDAS:
                coluna = "Escl. Dúvidas";
                break;
            case COL_CLAREZA:
                coluna = "Clareza";
                break;
            case COL_MOTIV_GRUPO:
                coluna = "Mot. grupo";
                break;
            case COL_PONTUALIDADE:
                coluna = "Pontualidade";
                break;
            default:
                throw new IllegalArgumentException("Coluna inválida");
        }
        return coluna;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == COL_CLAREZA || columnIndex == COL_DISTR_CH
                || columnIndex == COL_DOMINIO_ASSUNTO || columnIndex == COL_ESCL_DUVIDAS
                || columnIndex == COL_TEMA || columnIndex == COL_MOTIV_GRUPO
                || columnIndex == COL_PONTUALIDADE) {
            return String.class;
        }
        return null;
    }

    public Aval_curso get(int row) {
        return valores.get(row);
    }
}
