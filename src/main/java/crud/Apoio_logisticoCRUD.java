/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Apoio_logistico;
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
public class Apoio_logisticoCRUD {

    Conexao con = new Conexao();

    public boolean inserir(Apoio_logistico a) {
        try {
            con.abrirConexao();
            String sql = "INSERT INTO apoio_logistico"
                    + "(qualidade, adequacao, percentual_obj, criticas_sug, evento_id)"
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getQualidade());
            ps.setString(2, a.getAdequacao());
            ps.setString(3, a.getPercentual_obj());
            ps.setString(4, a.getCriticas_sug());
            ps.setInt(5, a.getEvento_id());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Apoio_logisticoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Apoio_logistico a) {
        try {
            con.abrirConexao();
            String sql = "UPDATE apoio_logistico SET qualidade=?, "
                    + "adequacao=?, percentual_obj=?, "
                    + "criticas_sug=? WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getQualidade());
            ps.setString(2, a.getAdequacao());
            ps.setString(3, a.getPercentual_obj());
            ps.setString(4, a.getCriticas_sug());
            ps.setInt(5, a.getId());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Apoio_logisticoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletar(Apoio_logistico e) {
        try {
            con.abrirConexao();
            String sql = "DELETE FROM apoio_logistico WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, e.getId());
            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Apoio_logisticoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Apoio_logistico obter(int id) {
        Apoio_logistico al = new Apoio_logistico();
        try {
            con.abrirConexao();
            String sql = "SELECT * FROM apoio_logistico WHERE id = ?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();

            if (rs != null) {
                al.setId(rs.getInt("id"));
                al.setEvento_id(rs.getInt("evento_id"));
                al.setAdequacao(rs.getString("adequacao"));
                al.setCriticas_sug(rs.getString("criticas_sug"));
                al.setPercentual_obj(rs.getString("percentual_obj"));
                al.setQualidade(rs.getString("qualidade"));
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Apoio_logisticoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public ArrayList<Apoio_logistico> obterTodos(Integer id) {
        ArrayList<Apoio_logistico> lista = new ArrayList<>();

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM apoio_logistico WHERE evento_id = ?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();

            if (rs != null) {
                do {
                    Apoio_logistico al = new Apoio_logistico();
                    al.setId(rs.getInt("id"));
                    al.setEvento_id(rs.getInt("evento_id"));
                    al.setAdequacao(rs.getString("adequacao"));
                    al.setCriticas_sug(rs.getString("criticas_sug"));
                    al.setPercentual_obj(rs.getString("percentual_obj"));
                    al.setQualidade(rs.getString("qualidade"));
                    lista.add(al);
                } while (rs.next());
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return lista;
    }

    public Float[] obterPercent(Integer id, String campo) {
        Float[] f = new Float[4];

        try {
            con.abrirConexao();
            String sql = "SELECT \n"
                    + "(SELECT COUNT(%) FROM apoio_logistico WHERE % = '1' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM apoio_logistico WHERE evento_id=?) as result\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM apoio_logistico WHERE % = '2' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM apoio_logistico WHERE evento_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM apoio_logistico WHERE % = '3' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM apoio_logistico WHERE evento_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM apoio_logistico WHERE % = '4' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM apoio_logistico WHERE evento_id=?)";
            sql = sql.replace("?", "" + id);
            sql = sql.replace("%", campo);

            Statement ps = con.conn.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            rs.first();
            if (rs != null) {
                for (int i = 0; i < 4; i++) {
                    f[i] = rs.getFloat("result");
                    rs.next();
                }
            }
            con.conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return f;
    }
}
