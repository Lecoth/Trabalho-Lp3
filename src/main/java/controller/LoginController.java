package controller;

import dao.UsuarioDAO;
import model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import db.UserSessao;
import java.io.IOException;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    public void entrar(ActionEvent event) {
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Email e senha são obrigatórios!");
            return;
        }

        try {
            Usuario usuario = usuarioDAO.fazerLogin(email, senha);

            if (usuario != null) {
                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Login realizado com sucesso!");
                UserSessao.setUsuarioLogado(usuario);
                abrirTelaPrincipal();
            } else {
                lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
                lblMensagem.setText("Email ou senha incorretos.");
            }
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    public void abrirCadastro(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/cadastro.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        stage.setScene(scene);
    }

    private void abrirTelaPrincipal() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) txtEmail.getScene().getWindow();
        stage.setScene(scene);
    }
}
