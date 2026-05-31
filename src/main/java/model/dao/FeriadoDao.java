package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DB.DB;
import model.classes.Feriado;

public class FeriadoDao {

    public FeriadoDao() {
    }

    public boolean inserir(Feriado feriado) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            con = DB.getConnection();
            String sql = "insert into feriado (dia, descricao) values (?, ?)";

            stmt = con.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(feriado.getDia().getTime()));
            stmt.setString(2, feriado.getDescricao());

            stmt.executeUpdate();
            result = true;
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection(con);
            return result;
        }
    }

    public boolean excluir(Feriado feriado) {
        Connection con = null;
        PreparedStatement stmt = null;
        boolean result = false;
        try {
            con = DB.getConnection();
            String sql = "delete from feriado where dia = ?";
            stmt = con.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(feriado.getDia().getTime()));
            stmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection(con);
            return result;
        }
    }

    public List<Feriado> listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Feriado> feriados = new ArrayList<>();
        String sql = "SELECT * FROM feriado ORDER BY dia";

        try {
            con = DB.getConnection();
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                java.util.Date dia = rs.getDate("dia");
                String descricao = rs.getString("descricao");
                Feriado feriado = new Feriado(dia, descricao);
                feriados.add(feriado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeResultSet(rs);
            DB.closeConnection(con);
        }
        return feriados;
    }
}
