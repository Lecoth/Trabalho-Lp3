package dao;

import db.Conexao;
import model.Arma;
import model.Artefato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtefatoDAO {
    public List<Artefato> buscarTodosArtefatos() {
        List<Artefato> artefatos = new ArrayList<>();
        String sql = "SELECT id_artefato, nome_set, efeito_2pecas, efeito_4pecas, imagem FROM artefato";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Artefato a = new Artefato();
                a.setId_artefato(rs.getInt("id_artefato"));
                a.setNome_set(rs.getString("nome_set"));
                a.setEfeito_2pecas(rs.getString("efeito_2pecas"));
                a.setEfeito_4pecas(rs.getString("efeito_4pecas"));
                a.setImagem(rs.getString("imagem"));

                artefatos.add(a);
            }
        } catch (Exception e) {
            System.out.println("Erro no ArtefatoDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return artefatos;
    }

    public void inserirArtefato(Artefato a) throws SQLException {
        String sql = "INSERT INTO artefato (nome_set, efeito_2pecas, efeito_4pecas, estrelas, imagem) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, a.getNome_set());
            stmt.setString(2, a.getEfeito_2pecas());
            stmt.setString(3, a.getEfeito_4pecas());
            stmt.setInt(4, a.getEstrelas());
            stmt.setString(5, a.getImagem());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no ArtefatoDAO ao inserir: " + e.getMessage());
            throw e;
        }
    }

    public void deletarArtefato(int idArtefato) throws SQLException {
        String sql = "DELETE FROM artefato WHERE id_artefato = ?";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idArtefato);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar artefato: " + e.getMessage());
            throw e;
        }
    }

    public void atualizarArtefato(Artefato a) throws SQLException {
        String sql = "UPDATE artefato SET nome_set=?, efeito_2pecas=?, efeito_4pecas=?, estrelas=?, imagem=? WHERE id_artefato=?";
        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, a.getNome_set());
            stmt.setString(2, a.getEfeito_2pecas());
            stmt.setString(3, a.getEfeito_4pecas());
            stmt.setInt(4, a.getEstrelas());
            stmt.setString(5, a.getImagem());
            stmt.setInt(6, a.getId_artefato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar artefato: " + e.getMessage());
            throw e;
        }
    }
}
