/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Aval_evento;
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
public class Aval_eventoCRUD {

    Conexao con = new Conexao();

    public boolean inserir(Aval_evento a) {
        try {
            con.abrirConexao();
            String sql = "INSERT INTO aval_evento (satisfacao, adequacao, "
                    + "abrangencia, relevancia, qualidade, "
                    + "atuac_coord, evento_id) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getSatisfacao());
            ps.setString(2, a.getAdequacao());
            ps.setString(3, a.getAbrangencia());
            ps.setString(4, a.getRelevancia());
            ps.setString(5, a.getQualidade());
            ps.setString(6, a.getAtuac_coord());
            ps.setInt(7, a.getEvento_id());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_eventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Aval_evento a) {
        try {
            con.abrirConexao();
            String sql = "UPDATE aval_evento SET satisfacao=?, adequacao=?, "
                    + "abrangencia=?, relevancia=?, qualidade=?, "
                    + "atuac_coord=? WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getSatisfacao());
            ps.setString(2, a.getAdequacao());
            ps.setString(3, a.getAbrangencia());
            ps.setString(4, a.getRelevancia());
            ps.setString(5, a.getQualidade());
            ps.setString(6, a.getAtuac_coord());
            ps.setInt(7, a.getId());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_eventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletar(Aval_evento e) {
        try {
            con.abrirConexao();
            String sql = "DELETE FROM aval_evento WHERE id=?";
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
            Logger.getLogger(Aval_eventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Aval_evento obter(int id) {
        Aval_evento ae = null;

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM aval_evento WHERE id = ?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();
            if (rs != null) {
                do {
                    ae = new Aval_evento();
                    ae.setId(rs.getInt("id"));
                    ae.setEvento_id(rs.getInt("evento_id"));
                    ae.setAbrangencia(rs.getString("abrangencia"));
                    ae.setAdequacao(rs.getString("adequacao"));
                    ae.setAtuac_coord(rs.getString("atuac_coord"));
                    ae.setQualidade(rs.getString("qualidade"));
                    ae.setRelevancia(rs.getString("relevancia"));
                    ae.setSatisfacao(rs.getString("satisfacao"));
                } while (rs.next());
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_eventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ae;
    }

    public ArrayList<Aval_evento> obterTodos(Integer id) {
        ArrayList<Aval_evento> lista = new ArrayList<>();

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM aval_evento WHERE evento_id = ?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();

            if (rs != null) {
                do {
                    Aval_evento ae = new Aval_evento();
                    ae.setId(rs.getInt("id"));
                    ae.setEvento_id(rs.getInt("evento_id"));
                    ae.setAbrangencia(rs.getString("abrangencia"));
                    ae.setAdequacao(rs.getString("adequacao"));
                    ae.setAtuac_coord(rs.getString("atuac_coord"));
                    ae.setQualidade(rs.getString("qualidade"));
                    ae.setRelevancia(rs.getString("relevancia"));
                    ae.setSatisfacao(rs.getString("satisfacao"));
                    lista.add(ae);
                } while (rs.next());
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_eventoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public Float[] obterPercent(Integer id, String campo) {
        Float[] f = new Float[4];

        try {
            con.abrirConexao();
            String sql = "SELECT \n"
                    + "(SELECT COUNT(%) FROM aval_evento WHERE % = '1' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_evento WHERE evento_id=?) as result\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_evento WHERE % = '2' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_evento WHERE evento_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_evento WHERE % = '3' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_evento WHERE evento_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_evento WHERE % = '4' and \n"
                    + "evento_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_evento WHERE evento_id=?)";
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
