package dao;

import db.Conexao;
import model.Favorito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritoDAO {

    public int contarFavoritos(int idUsuario) {
        String sql = "SELECT COUNT(*) AS total FROM favorito WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void adicionarFavorito(int idUsuario, Integer idBuildGuia, Integer idBuildUser) throws SQLException {
        if (contarFavoritos(idUsuario) >= 4) {
            throw new SQLException("Você atingiu o limite de 4 favoritos!");
        }

        String sql = "INSERT INTO favorito (id_usuario, id_build_guia, id_build_user) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            if (idBuildGuia != null) stmt.setInt(2, idBuildGuia);
            else stmt.setNull(2, java.sql.Types.INTEGER);

            if (idBuildUser != null) stmt.setInt(3, idBuildUser);
            else stmt.setNull(3, java.sql.Types.INTEGER);

            stmt.executeUpdate();
        }
    }

    public void removerFavorito(int idUsuario, Integer idBuildGuia, Integer idBuildUser) throws SQLException {
        String sql;
        if (idBuildGuia != null) {
            sql = "DELETE FROM favorito WHERE id_usuario = ? AND id_build_guia = ?";
        } else {
            sql = "DELETE FROM favorito WHERE id_usuario = ? AND id_build_user = ?";
        }

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, (idBuildGuia != null) ? idBuildGuia : idBuildUser);
            stmt.executeUpdate();
        }
    }

    public boolean isFavorito(int idUsuario, Integer idBuildGuia, Integer idBuildUser) {
        String sql;
        if (idBuildGuia != null) sql = "SELECT 1 FROM favorito WHERE id_usuario = ? AND id_build_guia = ?";
        else sql = "SELECT 1 FROM favorito WHERE id_usuario = ? AND id_build_user = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, (idBuildGuia != null) ? idBuildGuia : idBuildUser);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Favorito> listarFavoritos(int idUsuario) {
        List<Favorito> lista = new ArrayList<>();

        String sql = "SELECT 'Guia' as tipo, bg.id_build_guia as id, p.nome as nome_display " +
                "FROM favorito f JOIN build_guia bg ON f.id_build_guia = bg.id_build_guia " +
                "JOIN personagem p ON bg.id_personagem = p.id_personagem " +
                "WHERE f.id_usuario = ? " +
                "UNION " +
                "SELECT 'User' as tipo, bu.id_build_user as id, bu.nome_build as nome_display " +
                "FROM favorito f JOIN build_usuario bu ON f.id_build_user = bu.id_build_user " +
                "WHERE f.id_usuario = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String tipo = rs.getString("tipo");
                int id = rs.getInt("id");
                String nome = rs.getString("nome_display");
                lista.add(new Favorito(id, tipo, nome));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}