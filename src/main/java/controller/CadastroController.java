package controller;

import dao.UsuarioDAO;
import db.UserSessao;
import model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroController {
    @FXML
    private TextField txtNome, txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrar(ActionEvent event) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Todos os campos são obrigatórios!");
            return;
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha); // O dao vai lidar com a senha

        try {
            usuarioDAO.cadastrarUsuario(novoUsuario);

            Usuario usuarioLogado = usuarioDAO.fazerLogin(novoUsuario.getEmail(), novoUsuario.getSenha());

            if (usuarioLogado != null) {
                UserSessao.setUsuarioLogado(usuarioLogado);

                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Usuário cadastrado com sucesso! Logando...");

                abrirTelaPrincipal();
            } else {
                lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
                lblMensagem.setText("Cadastro realizado, mas falha ao logar. Tente na tela de login.");
            }

        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);

            if (e.getMessage().contains("Duplicate entry")) {
                lblMensagem.setText("Erro: Este e-mail já está cadastrado.");
            } else {
                lblMensagem.setText("Erro no banco de dados. Tente novamente.");
            }
        } catch (IOException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro ao cadastrar: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void abrirTelaPrincipal() throws java.io.IOException {
        javafx.fxml.FXMLLoader fxmlLoader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        javafx.scene.Scene scene = new javafx.scene.Scene(fxmlLoader.load());

        javafx.stage.Stage stage = (javafx.stage.Stage) txtNome.getScene().getWindow();

        stage.setScene(scene);
    }

    public void voltar(ActionEvent event) throws Exception {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        javafx.scene.Scene scene = new javafx.scene.Scene(loader.load());
        javafx.stage.Stage stage = (javafx.stage.Stage) txtNome.getScene().getWindow();
        stage.setScene(scene);
    }
}
