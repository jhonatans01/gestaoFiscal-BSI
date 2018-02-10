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
public class Tema_curso {

    private Integer id;
    private Integer evento_id;
    private String titulo;

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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Tema_curso{" + "id=" + id + ", evento_id=" + evento_id + ", titulo=" + titulo + '}';
    }

}
