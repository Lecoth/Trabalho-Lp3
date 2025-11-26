package controller;

import dao.BuildGuiaDAO;
import dao.FavoritoDAO;
import db.UserSessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.BuildGuia;
import model.BuildUserInfo;
import model.Favorito;
import model.Usuario;
import dao.BuildUsuarioDAO;

import java.io.IOException;
import java.util.List;

public class PerfilController {

    @FXML
    private Label lblNomeUsuario;
    @FXML
    private Label lblEmailUsuario;
    @FXML private ListView<String> listBuilds;
    @FXML private Label lblSemBuilds;

    @FXML private ListView<Favorito> listFavoritos;
    private FavoritoDAO favoritoDAO = new FavoritoDAO();
    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();
    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();

    @FXML
    public void initialize() {
        Usuario u = UserSessao.getUsuarioLogado();

        if (u != null) {
            lblNomeUsuario.setText(u.getNome());
            lblEmailUsuario.setText(u.getEmail());
            carregarListaDeBuilds(u.getIdUser());
            carregarFavoritos(u.getIdUser());
            listFavoritos.setOnMouseClicked(this::abrirFavorito);
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

    private void carregarFavoritos(int idUsuario) {
        List<Favorito> favoritos = favoritoDAO.listarFavoritos(idUsuario);
        listFavoritos.getItems().setAll(favoritos);
    }

    private void abrirFavorito(MouseEvent event) {
        Favorito selecionado = listFavoritos.getSelectionModel().getSelectedItem();

        if (selecionado == null || event.getClickCount() < 2) return;

        try {
            if (selecionado.getTipo().equals("Guia")) {
                BuildGuia build = buildGuiaDAO.buscarPorId(selecionado.getIdBuild());
                if (build != null) {
                    abrirPopup("/fxml/buildGuiaMolde.fxml", controller -> {
                        ((BuildCardController) controller).setBuild(build);
                    });
                }
            } else {
                BuildUserInfo build = buildUsuarioDAO.buscarPorId(selecionado.getIdBuild());
                if (build != null) {
                    abrirPopup("/fxml/userBuildMolde.fxml", controller -> {
                        UserBuildCardController c = (UserBuildCardController) controller;
                        c.setMostrarAutor(true);
                        c.setBuild(build);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void abrirPopup(String fxmlPath, java.util.function.Consumer<Object> configController) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = loader.load();

        configController.accept(loader.getController());

        Stage stage = new Stage();
        stage.setTitle("Detalhes do Favorito");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
