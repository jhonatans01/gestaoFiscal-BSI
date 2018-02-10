/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Aval_curso;
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
public class Aval_cursoCRUD {

    Conexao con = new Conexao();

    public boolean inserir(Aval_curso a) {
        try {
            con.abrirConexao();
            String sql = "INSERT INTO aval_curso (pontualidade, dominio_assunto, "
                    + "clareza, distr_ch, motiv_grupo, escl_duvidas, tema_curso_id, "
                    + "evento_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getPontualidade());
            ps.setString(2, a.getDominio_assunto());
            ps.setString(3, a.getClareza());
            ps.setString(4, a.getDistr_ch());
            ps.setString(5, a.getMotiv_grupo());
            ps.setString(6, a.getEscl_duvidas());
            ps.setInt(7, a.getTema_curso_id());
            ps.setInt(8, a.getEvento_id());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editar(Aval_curso a) {
        try {
            con.abrirConexao();
            String sql = "UPDATE aval_curso SET pontualidade=?, dominio_assunto=?, "
                    + "clareza=?, distr_ch=?, motiv_grupo=?, escl_duvidas=?, "
                    + "tema_curso_id=? WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setString(1, a.getPontualidade());
            ps.setString(2, a.getDominio_assunto());
            ps.setString(3, a.getClareza());
            ps.setString(4, a.getDistr_ch());
            ps.setString(5, a.getMotiv_grupo());
            ps.setString(6, a.getEscl_duvidas());
            ps.setInt(7, a.getTema_curso_id());
            ps.setInt(8, a.getId());

            int i = ps.executeUpdate();
            if (i > 0) {
                con.conn.close();
                return true;
            }

            con.conn.close();

        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletar(Aval_curso e) {
        try {
            con.abrirConexao();
            String sql = "DELETE FROM aval_curso WHERE id=?";
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
            Logger.getLogger(Aval_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Aval_curso obter(int id) {
        Aval_curso a = new Aval_curso();
        try {
            con.abrirConexao();
            String sql = "SELECT * FROM aval_curso WHERE id=?";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                rs.next();
                a.setId(rs.getInt("id"));
                a.setPontualidade(rs.getString("pontualidade"));
                a.setDominio_assunto(rs.getString("dominio_assunto"));
                a.setClareza(rs.getString("clareza"));
                a.setDistr_ch(rs.getString("distr_ch"));
                a.setMotiv_grupo(rs.getString("motiv_grupo"));
                a.setEscl_duvidas(rs.getString("escl_duvidas"));
                a.setTema_curso_id(rs.getInt("tema_curso_id"));
                a.setEvento_id(rs.getInt("evento_id"));
                con.conn.close();
            } else {
                con.conn.close();
                System.out.println("ERRO");
            }
            con.conn.close();
        } catch (SQLException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Aval_cursoCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ArrayList<Aval_curso> obterTodos(Integer id) {
        ArrayList<Aval_curso> lista = new ArrayList<>();

        try {
            con.abrirConexao();
            String sql = "SELECT * FROM aval_curso WHERE evento_id = ? "
                    + " ORDER BY tema_curso_id DESC";
            PreparedStatement ps = con.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.first();

            if (rs != null) {
                do {
                    Aval_curso ac = new Aval_curso();
                    ac.setId(rs.getInt("id"));
                    ac.setClareza(rs.getString("clareza"));
                    ac.setDistr_ch(rs.getString("distr_ch"));
                    ac.setDominio_assunto(rs.getString("dominio_assunto"));
                    ac.setEscl_duvidas(rs.getString("escl_duvidas"));
                    ac.setMotiv_grupo(rs.getString("motiv_grupo"));
                    ac.setPontualidade(rs.getString("pontualidade"));
                    ac.setTema_curso_id(rs.getInt("tema_curso_id"));
                    ac.setEvento_id(rs.getInt("evento_id"));
                    lista.add(ac);
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
                    + "(SELECT COUNT(%) FROM aval_curso WHERE % = '1' and \n"
                    + "tema_curso_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_curso WHERE tema_curso_id=?) as result\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_curso WHERE % = '2' and \n"
                    + "tema_curso_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_curso WHERE tema_curso_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_curso WHERE % = '3' and \n"
                    + "tema_curso_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_curso WHERE tema_curso_id=?)\n"
                    + "UNION ALL\n"
                    + "SELECT (SELECT COUNT(%) FROM aval_curso WHERE % = '4' and \n"
                    + "tema_curso_id=?)*100/\n"
                    + "(SELECT COUNT(*) FROM aval_curso WHERE tema_curso_id=?)";
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
