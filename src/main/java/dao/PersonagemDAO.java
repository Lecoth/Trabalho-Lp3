package dao;

import db.Conexao;
import model.Personagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
