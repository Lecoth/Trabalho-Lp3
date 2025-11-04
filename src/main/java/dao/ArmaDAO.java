package dao;

import db.Conexao;
import model.Arma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
