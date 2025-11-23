package dao;

import db.Conexao;
import model.Arma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmaDAO {

    public List<Arma> buscarTodasArmas() {
        List<Arma> armas = new ArrayList<>();
        String sql = "SELECT id_arma, nome, tipo_arma, base_atk, sub_status, efeito, imagem FROM arma";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arma a = new Arma();
                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome"));
                a.setTipo_arma(rs.getString("tipo_arma"));
                a.setBase_atk(rs.getInt("base_atk"));
                a.setSub_status(rs.getString("sub_status"));
                a.setEfeito(rs.getString("efeito"));
                a.setImagem(rs.getString("imagem"));

                armas.add(a);
            }
        } catch (Exception e) {
            System.out.println("Erro na ArmaDAO: " + e.getMessage());
            e.printStackTrace();
        }
        return armas;
    }

    public void inserirArma(Arma a) throws SQLException {
        String sql = "INSERT INTO arma (nome, tipo_arma, estrelas, base_atk, sub_status, efeito, imagem) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getTipo_arma());
            stmt.setInt(3, a.getEstrelas());
            stmt.setInt(4, a.getBase_atk());
            stmt.setString(5, a.getSub_status());
            stmt.setString(6, a.getEfeito());
            stmt.setString(7, a.getImagem());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no ArmaDAO ao inserir: " + e.getMessage());
            throw e;
        }
    }

    public List<Arma> buscarArmasPorTipo(String tipoArma) {
        List<Arma> armas = new ArrayList<>();
        String sql = "SELECT id_arma, nome, tipo_arma, estrelas, base_atk, sub_status, efeito, imagem FROM arma WHERE tipo_arma = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipoArma);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Arma a = new Arma();
                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome"));
                a.setTipo_arma(rs.getString("tipo_arma"));
                a.setEstrelas(rs.getInt("estrelas"));
                a.setBase_atk(rs.getInt("base_atk"));
                a.setSub_status(rs.getString("sub_status"));
                a.setEfeito(rs.getString("efeito"));
                a.setImagem(rs.getString("imagem"));

                armas.add(a);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar armas por tipo: " + e.getMessage());
            e.printStackTrace();
        }
        return armas;
    }
}
