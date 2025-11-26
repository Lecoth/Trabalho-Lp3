package dao;

import db.Conexao;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildGuiaDAO {
    public List<BuildGuia> buscarBuildGuiaPorPersonagem(String nomePersonagem) {
        List<BuildGuia> guiasEncontrados = new ArrayList<>();

        String sql = "SELECT " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.talentos, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.efeito, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.efeito_2pecas, art.efeito_4pecas, art.imagem AS imagem_artefato, " +
                "bg.id_build_guia, bg.main_sands, bg.main_goblet, bg.main_circlet, bg.substats, bg.ideal_status " +
                "FROM build_guia bg " +
                "JOIN personagem p ON bg.id_personagem = p.id_personagem " +
                "JOIN arma a ON bg.id_arma_ideal = a.id_arma " +
                "JOIN artefato art ON bg.id_art_set = art.id_artefato " +
                "WHERE UPPER(p.nome) LIKE UPPER(?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) return guiasEncontrados;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomePersonagem + "%");

            ResultSet rs = stmt.executeQuery();

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

                guiasEncontrados.add(build);
            }

        } catch (Exception e) {
            System.out.println("Erro no BuildGuiaDAO: " + e.getMessage());
            e.printStackTrace();
        }

        return guiasEncontrados;
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

    public void inserirBuildGuia(BuildGuia build) throws SQLException {
        // A tabela build_guia espera os ids, e os outros campos de texto.
        String sql = "INSERT INTO build_guia (id_personagem, id_arma_ideal, id_art_set, pq_arma_ideal, main_sands, main_goblet, main_circlet, substats, ideal_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Pega os ids
            stmt.setInt(1, build.getPersonagem().getId_personagem());
            stmt.setInt(2, build.getArma().getId_arma());
            stmt.setInt(3, build.getArtefato().getId_artefato());

            // Pega os campos de texto
            stmt.setString(4, build.getPq_arma_ideal());
            stmt.setString(5, build.getMain_sands());
            stmt.setString(6, build.getMain_goblet());
            stmt.setString(7, build.getMain_circlet());
            stmt.setString(8, build.getSubstatus());
            stmt.setString(9, build.getIdeal_status());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no BuildGuiaDAO ao inserir: " + e.getMessage());
            throw e;
        }
    }

    public void deletarBuildGuia(int idBuildGuia) throws SQLException {
        String sql = "DELETE FROM build_guia WHERE id_build_guia = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBuildGuia);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no BuildGuiaDAO ao DELETAR: " + e.getMessage());
            throw e;
        }
    }

    public void atualizarBuildGuia(BuildGuia build) throws SQLException {
        String sql = "UPDATE build_guia SET id_personagem = ?, id_arma_ideal = ?, id_art_set = ?, " +
                "pq_arma_ideal = ?, main_sands = ?, main_goblet = ?, main_circlet = ?, " +
                "substats = ?, ideal_status = ? WHERE id_build_guia = ?";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) throw new SQLException("Erro de conexão.");

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, build.getPersonagem().getId_personagem());
            stmt.setInt(2, build.getArma().getId_arma());
            stmt.setInt(3, build.getArtefato().getId_artefato());
            stmt.setString(4, build.getPq_arma_ideal());
            stmt.setString(5, build.getMain_sands());
            stmt.setString(6, build.getMain_goblet());
            stmt.setString(7, build.getMain_circlet());
            stmt.setString(8, build.getSubstatus());
            stmt.setString(9, build.getIdeal_status());
            stmt.setInt(10, build.getId_build_guia());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no BuildGuiaDAO ao ATUALIZAR: " + e.getMessage());
            throw e;
        }
    }

    public BuildGuia buscarPorId(int id) {
        String sql = "SELECT " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.talentos, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.efeito, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.efeito_2pecas, art.efeito_4pecas, art.imagem AS imagem_artefato, " +
                "bg.id_build_guia, bg.main_sands, bg.main_goblet, bg.main_circlet, bg.substats, bg.ideal_status " +
                "FROM build_guia bg " +
                "JOIN personagem p ON bg.id_personagem = p.id_personagem " +
                "JOIN arma a ON bg.id_arma_ideal = a.id_arma " +
                "JOIN artefato art ON bg.id_art_set = art.id_artefato " +
                "WHERE bg.id_build_guia = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
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
            System.out.println("Erro ao buscar BuildGuia por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}