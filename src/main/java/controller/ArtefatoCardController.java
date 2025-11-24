package controller;

import dao.ArtefatoDAO;
import db.UserSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Artefato;
import model.Usuario;
import utils.AlertUtils;

import java.io.IOException;

public class ArtefatoCardController {
    @FXML
    private ImageView imgArtefato;
    @FXML
    private Label lblNomeSet;
    @FXML
    private Text txt2pecas;
    @FXML
    private Text txt4pecas;
    @FXML private javafx.scene.layout.HBox adminBotoesHBox;
    @FXML private javafx.scene.layout.AnchorPane rootPane;

    private Artefato artefato;
    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private VBox parentVBox;

    public void setArtefato(Artefato a){
        this.artefato = a;
        if (artefato == null) return;

        lblNomeSet.setText(a.getNome_set());
        imgArtefato.setImage(carregarImagemSegura(a.getImagem()));
        txt2pecas.setText(a.getEfeito_2pecas());
        txt4pecas.setText(a.getEfeito_4pecas());

        Usuario u = UserSessao.getUsuarioLogado();
        boolean isAdmin = (u != null && u.isAdmin());

        if (adminBotoesHBox != null) {
            adminBotoesHBox.setVisible(isAdmin);
            adminBotoesHBox.setManaged(isAdmin);
        }
    }

    @FXML
    void deletarArtefato(ActionEvent event) {
        boolean confirmou = AlertUtils.mostrarConfirmacao("Deletar", "Excluir artefato " + artefato.getNome_set() + "?", "Isso não pode ser desfeito.", rootPane.getScene().getWindow());
        if (confirmou) {
            try {
                artefatoDAO.deletarArtefato(artefato.getId_artefato());
                if (parentVBox != null) parentVBox.getChildren().remove(rootPane);
                AlertUtils.mostrarSucesso("Sucesso", "Artefato deletada.");
            } catch (Exception e) {
                AlertUtils.mostrarErro("Erro", null, e.getMessage());
            }
        }
    }

    @FXML
    void editarArtefato(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminArtefato.fxml"));
            Node formNode = loader.load();

            AdminArtefatoController controller = loader.getController();
            controller.carregarParaEdicao(this.artefato);

            BorderPane mainPane = (BorderPane) rootPane.getScene().lookup("#mainPane");
            if (mainPane != null) mainPane.setCenter(formNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}
