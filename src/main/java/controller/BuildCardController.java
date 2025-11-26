package controller;

import dao.BuildGuiaDAO;
import dao.FavoritoDAO;
import db.UserSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BuildGuia;
import model.Usuario;
import utils.AlertUtils;
import utils.ImageUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class BuildCardController {

    @FXML private AnchorPane rootPane;
    @FXML private ImageView imgPersonagem, imgArma, imgArtefato;
    @FXML private Label lblNomePersonagem, lblElemento, lblNomeArma, lblNomeArtefato;
    @FXML private Text txtSand, txtGoblet, txtCirclet, txtSubstatusDesc;
    @FXML private HBox adminBotoesHBox;
    @FXML private Button btnFavoritar;

    private BuildGuia build;
    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();
    private FavoritoDAO favoritoDAO = new FavoritoDAO();
    private VBox parentVBox;

    public void setBuild(BuildGuia build) {
        this.build = build;
        if (build == null) return;

        lblNomePersonagem.setText(build.getPersonagem().getNome());
        lblElemento.setText(build.getPersonagem().getElemento());
        lblNomeArma.setText(build.getArma().getNome());
        lblNomeArtefato.setText(build.getArtefato().getNome_set());
        txtSand.setText(build.getMain_sands());
        txtGoblet.setText(build.getMain_goblet());
        txtCirclet.setText(build.getMain_circlet());
        txtSubstatusDesc.setText(build.getSubstatus());

        imgPersonagem.setImage(ImageUtils.carregarImagemSegura(getClass(), build.getPersonagem().getImagem()));
        imgArma.setImage(ImageUtils.carregarImagemSegura(getClass(), build.getArma().getImagem()));
        imgArtefato.setImage(ImageUtils.carregarImagemSegura(getClass(), build.getArtefato().getImagem()));

        // Verifica se é Admin para mostrar botões
        Usuario u = UserSessao.getUsuarioLogado();
        if (u != null && u.isAdmin()) {
            adminBotoesHBox.setVisible(true);
            adminBotoesHBox.setManaged(true);
        } else {
            adminBotoesHBox.setVisible(false);
            adminBotoesHBox.setManaged(false);
        }

        u = UserSessao.getUsuarioLogado();
        if (u != null) {

            if (favoritoDAO.isFavorito(u.getIdUser(), build.getId_build_guia(), null)) {
                btnFavoritar.setText("★ Favorito");
                btnFavoritar.setStyle("-fx-background-color: #FFD700;");
            } else {
                btnFavoritar.setText("☆ Favoritar");
                btnFavoritar.setStyle("");
            }
        }
    }

    public void setParentVBox(VBox parentVBox) {
        this.parentVBox = parentVBox;
    }

    @FXML
    void deletarBuild(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Build Guia");
        alert.setHeaderText("Deletar guia de: " + build.getPersonagem().getNome());
        alert.setContentText("Tem certeza? Essa ação é irreversível.");
        alert.initOwner(rootPane.getScene().getWindow());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                buildGuiaDAO.deletarBuildGuia(build.getId_build_guia());
                if (parentVBox != null) parentVBox.getChildren().remove(rootPane);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void editarBuild(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminBuildGuia.fxml"));
            Node formNode = loader.load();

            AdminBuildGuiaController controller = loader.getController();
            controller.carregarParaEdicao(this.build);

            BorderPane mainPane = (BorderPane) rootPane.getScene().lookup("#mainPane");
            if (mainPane != null) mainPane.setCenter(formNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void toggleFavorito(ActionEvent event) {
        Usuario u = UserSessao.getUsuarioLogado();
        if (u == null) return;

        try {
            if (btnFavoritar.getText().contains("Favorito")) {
                favoritoDAO.removerFavorito(u.getIdUser(), build.getId_build_guia(), null);
                btnFavoritar.setText("☆ Favoritar");
                btnFavoritar.setStyle("");
            } else {
                favoritoDAO.adicionarFavorito(u.getIdUser(), build.getId_build_guia(), null);
                btnFavoritar.setText("★ Favorito");
                btnFavoritar.setStyle("-fx-background-color: #FFD700;");
                AlertUtils.mostrarSucesso("Adicionado", "Build adicionada aos favoritos!");
            }
        } catch (SQLException e) {
            AlertUtils.mostrarErro("Erro", null, e.getMessage());
        }
    }
}