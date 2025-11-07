package dao;

import db.Conexao;
import model.Personagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    public List<Personagem> buscarTodosPersonagens() {
        List<Personagem> personagens = new ArrayList<>();
        String sql = "SELECT id_personagem, nome, imagem FROM personagem";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Personagem p = new Personagem();
                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome"));
                p.setImagem(rs.getString("imagem"));

                personagens.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro no PersonagemDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return personagens;
    }

    public void inserirPersonagem(Personagem p) throws SQLException {
        String sql = "INSERT INTO personagem (nome, elemento, tipo_arma, estrelas, talentos, imagem) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getElemento());
            stmt.setString(3, p.getTipo_arma());
            stmt.setInt(4, p.getEstrelas());
            stmt.setString(5, p.getTalentos());
            stmt.setString(6, p.getImagem());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no PersonagemDAO ao inserir: " + e.getMessage());
            throw e; // Lança a exceção para o controller poder tratá-la
        }
    }
}
