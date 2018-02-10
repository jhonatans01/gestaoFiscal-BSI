/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Evento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexao;

/**
 *
 * @author jhonatan
 */
public class EventoCRUD {

    Conexao con = new Conexao();

    public boolean inserir(Evento e) {
        try {
            con.abrirConexao();
            String sql = "INSERT INTO evento (titulo, dataI, dataF, local) VALUES (?,?,?,?)";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, e.getTitulo());
            ps.setDate(2, e.getDataI());
            ps.setDate(3, e.getDataF());
            ps.setString(4, e.getLocal());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Evento e) {
        try {
            con.abrirConexao();
            String sql = "UPDATE evento SET titulo=?, dataI=?, dataF=?, local=? WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, e.getTitulo());
            ps.setDate(2, e.getDataI());
            ps.setDate(3, e.getDataF());
            ps.setString(4, e.getLocal());
            ps.setInt(5, e.getId());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletar(Evento e) {
        try {
            con.abrirConexao();
            String sql1 = "DELETE FROM aval_evento WHERE evento_id = ?";
            String sql2 = "DELETE FROM aval_curso WHERE evento_id = ?";
            String sql3 = "DELETE FROM apoio_logistico WHERE evento_id = ?";
            String sql4 = "DELETE FROM tema_curso WHERE evento_id = ?";
            String sql5 = "DELETE FROM evento WHERE id=?";

            PreparedStatement ps = con.conn.prepareStatement(sql1);
            ps.setInt(1, e.getId());
            int i = ps.executeUpdate();
            ps = con.conn.prepareStatement(sql2);
            ps.setInt(1, e.getId());
            i = ps.executeUpdate();
            ps = con.conn.prepareStatement(sql3);
            ps.setInt(1, e.getId());
            i = ps.executeUpdate();
            ps = con.conn.prepareStatement(sql4);
            ps.setInt(1, e.getId());
            i = ps.executeUpdate();
            ps = con.conn.prepareStatement(sql5);
            ps.setInt(1, e.getId());
            i = ps.executeUpdate();

            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Evento obter(int id) {
        Evento e = new Evento();
        try {
            con.abrirConexao();
            String sql = "SELECT * FROM evento WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                rs.next();
                e.setId(rs.getInt("id"));
                e.setTitulo(rs.getString("titulo"));
                e.setDataI(rs.getDate("dataI"));
                e.setDataF(rs.getDate("dataF"));
                e.setLocal(rs.getString("local"));
                con.conn.close();
            } else {
                con.conn.close();
                System.out.println("ERRO");
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }

    public ArrayList<Evento> obterTodos() {
        ArrayList<Evento> dados = new ArrayList<>();

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM evento";
            Statement st = con.conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.first();

            if (rs != null) {
                do {
                    Evento e = new Evento();
                    e.setId(rs.getInt("id"));
                    e.setTitulo(rs.getString("titulo"));
                    e.setDataI(rs.getDate("dataI"));
                    e.setDataF(rs.getDate("dataF"));
                    e.setLocal(rs.getString("local"));
                    dados.add(e);
                } while (rs.next());

            } else {
                System.out.println("ERRO");
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dados;
    }
}
