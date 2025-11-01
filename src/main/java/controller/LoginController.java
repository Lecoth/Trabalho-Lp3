package controller;

import dao.UsuarioDAO;
import model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

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

        try {
            // 2. Chame o DAO. O SQL foi movido para cá.
            Usuario usuario = usuarioDAO.fazerLogin(email, senha);

            // 3. O DAO retorna 'null' se não encontrar
            if (usuario != null) {
                lblMensagem.setText("Login realizado com sucesso!");

                // (Opcional) Guarde o usuário logado para a tela principal
                // Ex: UserSession.setUsuarioLogado(usuario);

                abrirTelaPrincipal();
            } else {
                lblMensagem.setText("Email ou senha incorretos.");
            }
        } catch (Exception e) {
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
