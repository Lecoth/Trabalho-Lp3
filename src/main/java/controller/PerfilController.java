package controller;

import db.UserSessao;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.BuildUserInfo;
import model.Usuario;
import dao.BuildUsuarioDAO;
import java.util.List;

public class PerfilController {

    @FXML
    private Label lblNomeUsuario;
    @FXML
    private Label lblEmailUsuario;
    @FXML private ListView<String> listBuilds;
    @FXML private Label lblSemBuilds;

    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();

    @FXML
    public void initialize() {
        Usuario usuarioLogado = UserSessao.getUsuarioLogado();

        if (usuarioLogado != null) {
            lblNomeUsuario.setText(usuarioLogado.getNome());
            lblEmailUsuario.setText(usuarioLogado.getEmail());
            carregarListaDeBuilds(usuarioLogado.getIdUser());
        } else {
            lblNomeUsuario.setText("Usuário não encontrado");
            lblEmailUsuario.setText("Faça login novamente");
        }
    }

    private void carregarListaDeBuilds(int idUsuario) {
        List<BuildUserInfo> builds = buildUsuarioDAO.buscarBuildsInfosPorUsuario(idUsuario);

        if (builds.isEmpty()) {
            listBuilds.setVisible(false);
            listBuilds.setManaged(false);
            lblSemBuilds.setVisible(true);
            lblSemBuilds.setManaged(true);
        } else {
            listBuilds.setVisible(true);
            listBuilds.setManaged(true);
            lblSemBuilds.setVisible(false);
            lblSemBuilds.setManaged(false);

            for (BuildUserInfo b : builds) {
                listBuilds.getItems().add(b.getNome_build() + " (" + b.getPersonagem().getNome() + ")");
            }
        }
    }
}
