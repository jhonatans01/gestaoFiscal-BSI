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
public class Aval_evento {

    private Integer id;
    private Integer evento_id;
    private String satisfacao;
    private String adequacao;
    private String abrangencia;
    private String relevancia;
    private String qualidade;
    private String atuac_coord;

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

    public String getSatisfacao() {
        return satisfacao;
    }

    public void setSatisfacao(String satisfacao) {
        this.satisfacao = satisfacao;
    }

    public String getAdequacao() {
        return adequacao;
    }

    public void setAdequacao(String adequacao) {
        this.adequacao = adequacao;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(String relevancia) {
        this.relevancia = relevancia;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getAtuac_coord() {
        return atuac_coord;
    }

    public void setAtuac_coord(String atuac_coord) {
        this.atuac_coord = atuac_coord;
    }

    @Override
    public String toString() {
        return "Aval_evento{" + "id=" + id + ", evento_id=" + evento_id + ", satisfacao=" + satisfacao + ", adequacao=" + adequacao + ", abrangencia=" + abrangencia + ", relevancia=" + relevancia + ", qualidade=" + qualidade + ", atuac_coord=" + atuac_coord + '}';
    }

}
