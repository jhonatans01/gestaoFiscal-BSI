/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorio;

import crud.Apoio_logisticoCRUD;
import crud.Aval_cursoCRUD;
import crud.Aval_eventoCRUD;
import crud.EventoCRUD;
import crud.Tema_cursoCRUD;
import entity.Aval_curso;
import entity.Evento;
import entity.Tema_curso;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jhonatan
 */
public class Relatorio {

    private static EventoCRUD crudE = new EventoCRUD();
    private static Aval_eventoCRUD aeCrud = new Aval_eventoCRUD();
    private static Aval_cursoCRUD acCrud = new Aval_cursoCRUD();
    private static Apoio_logisticoCRUD alCrud = new Apoio_logisticoCRUD();
    private static Tema_cursoCRUD tcCrud = new Tema_cursoCRUD();

    public static void rel(Integer id) {

        List lista = new ArrayList();
        Evento e = crudE.obter(id);
        Map parameters = new HashMap();
        Map record = new HashMap();
        parameters.put("titulo", e.getTitulo());
        parameters.put("dataI", e.getDataI());
        parameters.put("dataF", e.getDataF());
        parameters.put("local", e.getLocal());

        ArrayList<Tema_curso> tc = tcCrud.obterTodos(id);
        ArrayList<Aval_curso> ac = acCrud.obterTodos(id);

        for (int i = 0; i < tc.size(); i++) {
            record.put("t_titulo", tc.get(i).getTitulo());
            System.out.println(record.get("t_titulo"));

            Float[] pont = acCrud.obterPercent(tc.get(i).getId(), "pontualidade"),
                    dominio = acCrud.obterPercent(tc.get(i).getId(), "dominio_assunto"),
                    clareza = acCrud.obterPercent(tc.get(i).getId(), "clareza"),
                    distr_ch = acCrud.obterPercent(tc.get(i).getId(), "distr_ch"),
                    motiv_grupo = acCrud.obterPercent(tc.get(i).getId(), "motiv_grupo"),
                    escl_duvidas = acCrud.obterPercent(tc.get(i).getId(), "escl_duvidas");

            record.put("p1", pont[0] + " %");
            record.put("p2", pont[1] + " %");
            record.put("p3", pont[2] + " %");
            record.put("p4", pont[3] + " %");
            record.put("d1", dominio[0] + " %");
            record.put("d2", dominio[1] + " %");
            record.put("d3", dominio[2] + " %");
            record.put("d4", dominio[3] + " %");
            record.put("c1", clareza[0] + " %");
            record.put("c2", clareza[1] + " %");
            record.put("c3", clareza[2] + " %");
            record.put("c4", clareza[3] + " %");
            record.put("dch1", distr_ch[0] + " %");
            record.put("dch2", distr_ch[1] + " %");
            record.put("dch3", distr_ch[2] + " %");
            record.put("dch4", distr_ch[3] + " %");
            record.put("m1", motiv_grupo[0] + " %");
            record.put("m2", motiv_grupo[1] + " %");
            record.put("m3", motiv_grupo[2] + " %");
            record.put("m4", motiv_grupo[3] + " %");
            record.put("e1", escl_duvidas[0] + " %");
            record.put("e2", escl_duvidas[1] + " %");
            record.put("e3", escl_duvidas[2] + " %");
            record.put("e4", escl_duvidas[3] + " %");

            lista.add(record);
        }

        Float[] sat = aeCrud.obterPercent(id, "satisfacao"),
                adeq_ae = aeCrud.obterPercent(id, "adequacao"),
                abr = aeCrud.obterPercent(id, "abrangencia"),
                rel = aeCrud.obterPercent(id, "relevancia"),
                qua_ae = aeCrud.obterPercent(id, "qualidade"),
                atuac = aeCrud.obterPercent(id, "atuac_coord");

        Float[] qua_al = alCrud.obterPercent(id, "qualidade"),
                ad_al = alCrud.obterPercent(id, "adequacao");

        parameters.put("s1", sat[0] + "%");
        parameters.put("s2", sat[1] + "%");
        parameters.put("s3", sat[2] + "%");
        parameters.put("s4", sat[3] + "%");
        parameters.put("a1", abr[0] + "%");
        parameters.put("a2", abr[1] + "%");
        parameters.put("a3", abr[2] + "%");
        parameters.put("a4", abr[3] + "%");
        parameters.put("r1", rel[0] + "%");
        parameters.put("r2", rel[1] + "%");
        parameters.put("r3", rel[2] + "%");
        parameters.put("r4", rel[3] + "%");
        parameters.put("q1", qua_ae[0] + "%");
        parameters.put("q2", qua_ae[1] + "%");
        parameters.put("q3", qua_ae[2] + "%");
        parameters.put("q4", qua_ae[3] + "%");
        parameters.put("ac1", atuac[0] + "%");
        parameters.put("ac2", atuac[1] + "%");
        parameters.put("ac3", atuac[2] + "%");
        parameters.put("ac4", atuac[3] + "%");

        parameters.put("al_q1", qua_al[0] + " %");
        parameters.put("al_q2", qua_al[1] + " %");
        parameters.put("al_q3", qua_al[2] + " %");
        parameters.put("al_q4", qua_al[3] + " %");
        parameters.put("al_a1", ad_al[0] + " %");
        parameters.put("al_a2", ad_al[1] + " %");
        parameters.put("al_a3", ad_al[2] + " %");
        parameters.put("al_a4", ad_al[3] + " %");

        parameters.put("id", e.getId());

        try {
            File arq = new File(System.getProperty("user.dir"));

            InputStream report = new FileInputStream(arq
                    + "/src/relatorio/teste.jasper");
            JasperPrint print
                    = JasperFillManager.fillReport(report, parameters,
                            new JRBeanCollectionDataSource(lista, false));
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setZoomRatio((float) 0.45);
            viewer.setTitle("Relatório");
            viewer.setSize(800, 700);
            viewer.setLocation(20, 300);
            viewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
            viewer.show();

            System.out.println("Relatório gerado.");
        } catch (JRException | FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getCause() + "\n" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        rel(4);
    }

}
