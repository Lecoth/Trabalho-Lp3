package dao;

import db.Conexao;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Classe só fala com o banco de dados
public class UsuarioDAO {

    public void cadastrarUsuario(Usuario novoUsuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                throw new SQLException("Não foi possível conectar ao banco de dados.");
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            // Usa os gets do usuario
            stmt.setString(1, novoUsuario.getNome());
            stmt.setString(2, novoUsuario.getEmail());
            stmt.setString(3, novoUsuario.getSenha());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro no DAO ao cadastrar: " + e.getMessage());
            // Exceção para o controller saber de erro
            throw e;
        }
    }

    public Usuario fazerLogin(String email, String senha) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar()) {
            if (conn == null) {
                System.out.println("Erro no DAO: Não foi possível conectar.");
                return null;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            // Se o ResultSet encontrou um usuário
            if (rs.next()) {
                // Cria Usuario com os dados do banco
                Usuario usuario = new Usuario();
                usuario.setIdUser(rs.getInt("idUser"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setAdmin(rs.getBoolean("is_admin"));
                // Não colocando a senha do objeto por segurança

                // Retorna usuario preenchido
                return usuario;
            }

        } catch (Exception e) {
            System.out.println("Erro no DAO ao fazer login: " + e.getMessage());
        }

        // Retorna nulo se o login falhou(não achou)ou se deu erro
        return null;
    }
}
