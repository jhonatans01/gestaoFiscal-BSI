/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Tema_curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexao;

/**
 *
 * @author jhonatan
 */
public class Tema_cursoCRUD {

    Conexao con = new Conexao();

    public boolean inserir(Tema_curso tc) {
        try {
            con.abrirConexao();
            String sql = "INSERT INTO tema_curso (titulo, evento_id) VALUES (?,?)";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, tc.getTitulo());
            ps.setInt(2, tc.getEvento_id());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tema_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Tema_curso tc) {
        try {
            con.abrirConexao();
            String sql = "UPDATE tema_curso SET titulo=? WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, tc.getTitulo());
            ps.setInt(2, tc.getId());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tema_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletar(Tema_curso tc) {
        try {
            con.abrirConexao();
            String sql = "DELETE FROM tema_curso WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, tc.getId());
            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tema_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Tema_curso obter(int id) {
        Tema_curso tc = new Tema_curso();
        try {
            con.abrirConexao();
            String sql = "SELECT * FROM tema_curso WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                rs.next();
                tc.setId(rs.getInt("id"));
                tc.setTitulo(rs.getString("titulo"));
                tc.setEvento_id(rs.getInt("evento_id"));
                con.conn.close();
            } else {
                con.conn.close();
                System.out.println("ERRO");
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tema_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tc;
    }

    public ArrayList<Tema_curso> obterTodos(Integer id) {
        ArrayList<Tema_curso> listaTc = new ArrayList<>();

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM tema_curso WHERE evento_id = ?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();

            if (rs != null) {
                do {
                    Tema_curso tc = new Tema_curso();
                    tc.setId(rs.getInt("id"));
                    tc.setTitulo(rs.getString("titulo"));
                    tc.setEvento_id(rs.getInt("evento_id"));
                    listaTc.add(tc);
                } while (rs.next());
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tema_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTc;
    }
}
