package controller;

import db.UserSessao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Usuario;

public class PerfilController {

    @FXML
    private Label lblNomeUsuario;

    @FXML
    private Label lblEmailUsuario;

    @FXML
    public void initialize() {
        Usuario usuarioLogado = UserSessao.getUsuarioLogado();

        if (usuarioLogado != null) {
            lblNomeUsuario.setText(usuarioLogado.getNome());
            lblEmailUsuario.setText(usuarioLogado.getEmail());
        } else {
            lblNomeUsuario.setText("Usuário não encontrado");
            lblEmailUsuario.setText("Faça login novamente");
        }
    }
}
