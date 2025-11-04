package dao;

import db.Conexao;
import model.Artefato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
