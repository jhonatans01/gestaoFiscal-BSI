/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author jhonatan
 */
public class Apoio_logistico {

    private Integer id;
    private Integer evento_id;
    private String qualidade;
    private String adequacao;
    private String percentual_obj;
    private String criticas_sug;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvento_id() {
        return evento_id;
    }

    public void setEvento_id(Integer evento_id) {
        this.evento_id = evento_id;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getAdequacao() {
        return adequacao;
    }

    public void setAdequacao(String adequacao) {
        this.adequacao = adequacao;
    }

    public String getPercentual_obj() {
        return percentual_obj;
    }

    public void setPercentual_obj(String percentual_obj) {
        this.percentual_obj = percentual_obj;
    }

    public String getCriticas_sug() {
        return criticas_sug;
    }

    public void setCriticas_sug(String criticas_sug) {
        this.criticas_sug = criticas_sug;
    }

    @Override
    public String toString() {
        return "Apoio_logistico{" + "id=" + id + ", evento_id=" + evento_id + ", qualidade=" + qualidade + ", adequacao=" + adequacao + ", percentual_obj=" + percentual_obj + ", criticas_sug=" + criticas_sug + '}';
    }

}
