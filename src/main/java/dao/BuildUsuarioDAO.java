package dao;

import db.Conexao;
import model.BuildUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildUsuarioDAO {

    public void inserirBuildUsuario(BuildUsuario build) throws SQLException {
        // A tabela 'build_usuario' espera os ids, nome, etc.
        // A data_criacao é preenchida automaticamente pelo banco (NOW())
        String sql = "INSERT INTO build_usuario (id_usuario, id_personagem, id_arma_usada, id_art_set, " +
                "nome_build, privada, sands_main, goblet_main, circlet_main, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Define os valores com base no objeto BuildUsuario
            stmt.setInt(1, build.getId_usuario());
            stmt.setInt(2, build.getId_personagem());
            stmt.setInt(3, build.getId_arma_usada());
            stmt.setInt(4, build.getId_art_set());
            stmt.setString(5, build.getNome_build());
            stmt.setBoolean(6, build.isPrivada());
            stmt.setString(7, build.getSands_main());
            stmt.setString(8, build.getGoblet_main());
            stmt.setString(9, build.getCirclet_main());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no BuildUsuarioDAO ao inserir: " + e.getMessage());
            throw e; // Lança a exceção para o controller
        }
    }

    // --- Futuramente, adicionar aqui: ---
    // public List<BuildUsuario> buscarBuildsPorUsuario(int idUsuario) { ... }
    // public List<BuildUsuario> buscarBuildsPublicas() { ... }
}