package controller;

import model.Conexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadastroController {
    @FXML
    private TextField txtNome, txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    public void cadastrar(ActionEvent event) {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        //correção de erro da possibilidade de não preencher todos os campos
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Todos os campos são obrigatórios!");

            return;
        }

        try (Connection conn = Conexao.conectar()) {
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.executeUpdate();

            lblMensagem.setText("Usuário cadastrado com sucesso!");
            abrirTelaPrincipal();
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
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
