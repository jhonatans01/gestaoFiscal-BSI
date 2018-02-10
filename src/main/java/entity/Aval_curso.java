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
public class Aval_curso {

    private Integer id;
    private Integer evento_id;
    private Integer tema_curso_id;
    private String pontualidade;
    private String dominio_assunto;
    private String clareza;
    private String distr_ch;
    private String motiv_grupo;
    private String escl_duvidas;

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

    public Integer getTema_curso_id() {
        return tema_curso_id;
    }

    public void setTema_curso_id(Integer tema_curso_id) {
        this.tema_curso_id = tema_curso_id;
    }

    public String getPontualidade() {
        return pontualidade;
    }

    public void setPontualidade(String pontualidade) {
        this.pontualidade = pontualidade;
    }

    public String getDominio_assunto() {
        return dominio_assunto;
    }

    public void setDominio_assunto(String dominio_assunto) {
        this.dominio_assunto = dominio_assunto;
    }

    public String getClareza() {
        return clareza;
    }

    public void setClareza(String clareza) {
        this.clareza = clareza;
    }

    public String getDistr_ch() {
        return distr_ch;
    }

    public void setDistr_ch(String distr_ch) {
        this.distr_ch = distr_ch;
    }

    public String getMotiv_grupo() {
        return motiv_grupo;
    }

    public void setMotiv_grupo(String motiv_grupo) {
        this.motiv_grupo = motiv_grupo;
    }

    public String getEscl_duvidas() {
        return escl_duvidas;
    }

    public void setEscl_duvidas(String escl_duvidas) {
        this.escl_duvidas = escl_duvidas;
    }

    @Override
    public String toString() {
        return "Aval_curso{" + "id=" + id + ", evento_id=" + evento_id + ", tema_curso_id=" + tema_curso_id + ", pontualidade=" + pontualidade + ", dominio_assunto=" + dominio_assunto + ", clareza=" + clareza + ", distr_ch=" + distr_ch + ", motiv_grupo=" + motiv_grupo + ", escl_duvidas=" + escl_duvidas + '}';
    }

}
