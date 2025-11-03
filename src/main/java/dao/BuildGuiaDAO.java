package dao;

import db.Conexao;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BuildGuiaDAO {
    public BuildGuia buscarBuildGuiaPorPersonagem(String nomePersonagem) {
        String sql = "SELECT " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.talentos, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.efeito, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.efeito_2pecas, art.efeito_4pecas, art.imagem AS imagem_artefato, " +
                "bg.id_build_guia, bg.main_sands, bg.main_goblet, bg.main_circlet, bg.substats, bg.ideal_status " +
                "FROM build_guia bg " +
                "JOIN personagem p ON bg.id_personagem = p.id_personagem " +
                "JOIN arma a ON bg.id_arma_ideal = a.id_arma " +
                "JOIN artefato art ON bg.id_art_set = art.id_artefato " +
                "WHERE UPPER(p.nome) = UPPER(?)"; // Busca pelo nome do personagem

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                System.out.println("Erro no DAO: Não foi possível conectar.");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomePersonagem.toUpperCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Personagem p = new Personagem();
                Arma a = new Arma();
                Artefato art = new Artefato();
                BuildGuia build = new BuildGuia();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setTalentos(rs.getString("talentos"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setEfeito(rs.getString("efeito"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setEfeito_2pecas(rs.getString("efeito_2pecas"));
                art.setEfeito_4pecas(rs.getString("efeito_4pecas"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_guia(rs.getInt("id_build_guia"));
                build.setMain_sands(rs.getString("main_sands"));
                build.setMain_goblet(rs.getString("main_goblet"));
                build.setMain_circlet(rs.getString("main_circlet"));
                build.setSubstatus(rs.getString("substats"));
                build.setIdeal_status(rs.getString("ideal_status"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                return build;
            }

        } catch (Exception e) {
            System.out.println("Erro no BuildGuiaDAO: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public List<BuildGuia> buscarTodasBuildsGuia() {
        List<BuildGuia> builds = new ArrayList<>();
        String sql = "SELECT " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.talentos, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.efeito, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.efeito_2pecas, art.efeito_4pecas, art.imagem AS imagem_artefato, " +
                "bg.id_build_guia, bg.main_sands, bg.main_goblet, bg.main_circlet, bg.substats, bg.ideal_status " +
                "FROM build_guia bg " +
                "JOIN personagem p ON bg.id_personagem = p.id_personagem " +
                "JOIN arma a ON bg.id_arma_ideal = a.id_arma " +
                "JOIN artefato art ON bg.id_art_set = art.id_artefato";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Usei WHILE em vez de IF para pegar todas as builds
            while (rs.next()) {
                Personagem p = new Personagem();
                Arma a = new Arma();
                Artefato art = new Artefato();
                BuildGuia build = new BuildGuia();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setTalentos(rs.getString("talentos"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setEfeito(rs.getString("efeito"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setEfeito_2pecas(rs.getString("efeito_2pecas"));
                art.setEfeito_4pecas(rs.getString("efeito_4pecas"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_guia(rs.getInt("id_build_guia"));
                build.setMain_sands(rs.getString("main_sands"));
                build.setMain_goblet(rs.getString("main_goblet"));
                build.setMain_circlet(rs.getString("main_circlet"));
                build.setSubstatus(rs.getString("substats"));
                build.setIdeal_status(rs.getString("ideal_status"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                // Adiciona a build preenchida na lista
                builds.add(build);
            }
        } catch (Exception e) {
            System.out.println("Erro no BuildGuiaDAO (buscarTodas): " + e.getMessage());
            e.printStackTrace();
        }
        return builds;
    }
}