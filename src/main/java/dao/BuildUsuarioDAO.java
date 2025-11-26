package dao;

import db.Conexao;
import model.Artefato;
import model.Arma;
import model.Personagem;
import model.BuildUserInfo;
import model.BuildUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildUsuarioDAO {

    public void inserirBuildUsuario(BuildUsuario build) throws SQLException {
        // A tabela 'build_usuario' espera os ids, nome, etc.
        // A data_criacao é preenchida automaticamente pelo banco
        String sql = "INSERT INTO build_usuario (id_usuario, id_personagem, id_arma_usada, id_art_set, " +
                "nome_build, privada, sands_main, goblet_main, circlet_main, descricao, data_criacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, build.getId_usuario());
            stmt.setInt(2, build.getId_personagem());
            stmt.setInt(3, build.getId_arma_usada());
            stmt.setInt(4, build.getId_art_set());
            stmt.setString(5, build.getNome_build());
            stmt.setBoolean(6, build.isPrivada());
            stmt.setString(7, build.getSands_main());
            stmt.setString(8, build.getGoblet_main());
            stmt.setString(9, build.getCirclet_main());
            stmt.setString(10, build.getDescricao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no BuildUsuarioDAO ao inserir: " + e.getMessage());
            throw e;
        }
    }

    public List<BuildUserInfo> buscarBuildsInfosPorUsuario(int idUsuario) {
        List<BuildUserInfo> builds = new ArrayList<>();

        String sql = "SELECT " +
                "bu.id_build_user, bu.nome_build, bu.privada, bu.sands_main, bu.goblet_main, bu.circlet_main, bu.descricao, " +
                "u.idUser, u.nome AS nome_usuario, " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.imagem AS imagem_artefato " +
                "FROM build_usuario bu " +
                "JOIN usuarios u ON bu.id_usuario = u.idUser " +
                "JOIN personagem p ON bu.id_personagem = p.id_personagem " +
                "JOIN arma a ON bu.id_arma_usada = a.id_arma " +
                "JOIN artefato art ON bu.id_art_set = art.id_artefato " +
                "WHERE bu.id_usuario = ?"; // Filtra pelo id do usuário logado

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BuildUserInfo build = new BuildUserInfo();
                Personagem p = new Personagem();
                Arma a = new Arma();
                Artefato art = new Artefato();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_user(rs.getInt("id_build_user"));
                build.setId_usuario(rs.getInt("idUser"));
                build.setNome_usuario(rs.getString("nome_usuario")); // Nome de quem criou a build
                build.setNome_build(rs.getString("nome_build")); // Título da build
                build.setPrivada(rs.getBoolean("privada"));
                build.setSands_main(rs.getString("sands_main"));
                build.setGoblet_main(rs.getString("goblet_main"));
                build.setCirclet_main(rs.getString("circlet_main"));
                build.setDescricao(rs.getString("descricao"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                builds.add(build);
            }
        } catch (Exception e) {
            System.out.println("Erro no BuildUsuarioDAO (buscar por usuário): " + e.getMessage());
            e.printStackTrace();
        }
        return builds;
    }

    public List<BuildUserInfo> buscarBuildsInfosPublicas() {
        List<BuildUserInfo> builds = new ArrayList<>();

        String sql = "SELECT " +
                "bu.id_build_user, bu.nome_build, bu.privada, bu.sands_main, bu.goblet_main, bu.circlet_main, bu.descricao, " +
                "u.idUser, u.nome AS nome_usuario, " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.imagem AS imagem_artefato " +
                "FROM build_usuario bu " +
                "JOIN usuarios u ON bu.id_usuario = u.idUser " +
                "JOIN personagem p ON bu.id_personagem = p.id_personagem " +
                "JOIN arma a ON bu.id_arma_usada = a.id_arma " +
                "JOIN artefato art ON bu.id_art_set = art.id_artefato " +
                "WHERE bu.privada = 0"; // Builds não privadas

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                BuildUserInfo build = new BuildUserInfo();
                Personagem p = new Personagem();
                Arma a = new Arma();
                Artefato art = new Artefato();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_user(rs.getInt("id_build_user"));
                build.setId_usuario(rs.getInt("idUser"));
                build.setNome_usuario(rs.getString("nome_usuario"));
                build.setNome_build(rs.getString("nome_build"));
                build.setPrivada(rs.getBoolean("privada"));
                build.setSands_main(rs.getString("sands_main"));
                build.setGoblet_main(rs.getString("goblet_main"));
                build.setCirclet_main(rs.getString("circlet_main"));
                build.setDescricao(rs.getString("descricao"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                builds.add(build);
            }
        } catch (Exception e) {
            System.out.println("Erro no BuildUsuarioDAO (buscar públicas): " + e.getMessage());
            e.printStackTrace();
        }
        return builds;
    }

    public List<BuildUserInfo> buscarBuildsPublicasPorNome(String nomeBuild) {
        List<BuildUserInfo> builds = new ArrayList<>();
        String sql = "SELECT " +
                "bu.id_build_user, bu.nome_build, bu.privada, bu.sands_main, bu.goblet_main, bu.circlet_main, bu.descricao, " +
                "u.idUser, u.nome AS nome_usuario, " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.imagem AS imagem_artefato " +
                "FROM build_usuario bu " +
                "JOIN usuarios u ON bu.id_usuario = u.idUser " +
                "JOIN personagem p ON bu.id_personagem = p.id_personagem " +
                "JOIN arma a ON bu.id_arma_usada = a.id_arma " +
                "JOIN artefato art ON bu.id_art_set = art.id_artefato " +
                "WHERE bu.privada = 0 AND bu.nome_build LIKE ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nomeBuild + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                BuildUserInfo build = new BuildUserInfo();
                Personagem p = new Personagem();
                Arma a = new Arma();
                Artefato art = new Artefato();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_user(rs.getInt("id_build_user"));
                build.setId_usuario(rs.getInt("idUser"));
                build.setNome_usuario(rs.getString("nome_usuario"));
                build.setNome_build(rs.getString("nome_build"));
                build.setPrivada(rs.getBoolean("privada"));
                build.setSands_main(rs.getString("sands_main"));
                build.setGoblet_main(rs.getString("goblet_main"));
                build.setCirclet_main(rs.getString("circlet_main"));
                build.setDescricao(rs.getString("descricao"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                builds.add(build);
            }
        } catch (Exception e) {
            System.out.println("Erro no BuildUsuarioDAO (buscar por nome): " + e.getMessage());
            e.printStackTrace();
        }
        return builds;
    }

    public void deletarBuildUsuario(int idBuildUser) throws SQLException {
        String sql = "DELETE FROM build_usuario WHERE id_build_user = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idBuildUser);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no BuildUsuarioDAO ao DELETAR: " + e.getMessage());
            throw e;
        }
    }

    public void atualizarBuildUsuario(BuildUsuario build) throws SQLException {
        String sql = "UPDATE build_usuario SET " +
                "id_personagem = ?, " +
                "id_arma_usada = ?, " +
                "id_art_set = ?, " +
                "nome_build = ?, " +
                "privada = ?, " +
                "sands_main = ?, " +
                "goblet_main = ?, " +
                "circlet_main = ?, " +
                "descricao = ? " +
                "WHERE id_build_user = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, build.getId_personagem());
            stmt.setInt(2, build.getId_arma_usada());
            stmt.setInt(3, build.getId_art_set());
            stmt.setString(4, build.getNome_build());
            stmt.setBoolean(5, build.isPrivada());
            stmt.setString(6, build.getSands_main());
            stmt.setString(7, build.getGoblet_main());
            stmt.setString(8, build.getCirclet_main());
            stmt.setString(9, build.getDescricao());
            stmt.setInt(10, build.getId_build_user());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro no BuildUsuarioDAO ao ATUALIZAR: " + e.getMessage());
            throw e;
        }
    }

    public model.BuildUserInfo buscarPorId(int id) {
        String sql = "SELECT " +
                "bu.id_build_user, bu.nome_build, bu.privada, bu.sands_main, bu.goblet_main, bu.circlet_main, bu.descricao, " +
                "u.idUser, u.nome AS nome_usuario, " +
                "p.id_personagem, p.nome AS nome_personagem, p.elemento, p.imagem AS imagem_personagem, " +
                "a.id_arma, a.nome AS nome_arma, a.imagem AS imagem_arma, " +
                "art.id_artefato, art.nome_set, art.imagem AS imagem_artefato " +
                "FROM build_usuario bu " +
                "JOIN usuarios u ON bu.id_usuario = u.idUser " +
                "JOIN personagem p ON bu.id_personagem = p.id_personagem " +
                "JOIN arma a ON bu.id_arma_usada = a.id_arma " +
                "JOIN artefato art ON bu.id_art_set = art.id_artefato " +
                "WHERE bu.id_build_user = ?";

        try (Connection conn = Conexao.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                model.BuildUserInfo build = new model.BuildUserInfo();
                model.Personagem p = new model.Personagem();
                model.Arma a = new model.Arma();
                model.Artefato art = new model.Artefato();

                p.setId_personagem(rs.getInt("id_personagem"));
                p.setNome(rs.getString("nome_personagem"));
                p.setElemento(rs.getString("elemento"));
                p.setImagem(rs.getString("imagem_personagem"));

                a.setId_arma(rs.getInt("id_arma"));
                a.setNome(rs.getString("nome_arma"));
                a.setImagem(rs.getString("imagem_arma"));

                art.setId_artefato(rs.getInt("id_artefato"));
                art.setNome_set(rs.getString("nome_set"));
                art.setImagem(rs.getString("imagem_artefato"));

                build.setId_build_user(rs.getInt("id_build_user"));
                build.setId_usuario(rs.getInt("idUser"));
                build.setNome_usuario(rs.getString("nome_usuario"));
                build.setNome_build(rs.getString("nome_build"));
                build.setPrivada(rs.getBoolean("privada"));
                build.setSands_main(rs.getString("sands_main"));
                build.setGoblet_main(rs.getString("goblet_main"));
                build.setCirclet_main(rs.getString("circlet_main"));
                build.setDescricao(rs.getString("descricao"));

                build.setPersonagem(p);
                build.setArma(a);
                build.setArtefato(art);

                return build;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar BuildUsuario por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}