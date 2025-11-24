package controller;

import dao.ArmaDAO;
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
import model.Arma;
import model.Usuario;
import utils.AlertUtils;
import utils.ImageUtils;

import java.io.IOException;

public class ArmaCardController {
    @FXML
    private ImageView imgArma;
    @FXML
    private Label lblNomeArma;
    @FXML
    private Text txtAtk;
    @FXML
    private Text txtDesc;
    @FXML
    private  Text txtDescEfeito;
    @FXML private javafx.scene.layout.HBox adminBotoesHBox;
    @FXML private javafx.scene.layout.AnchorPane rootPane;

    private Arma arma;
    private ArmaDAO armaDAO = new ArmaDAO();
    private VBox parentVBox;

    public void setArma(Arma a){
        this.arma = a;
        if (arma == null) return;

        lblNomeArma.setText(a.getNome());
        imgArma.setImage(carregarImagemSegura(a.getImagem()));
        txtAtk.setText(String.valueOf(a.getBase_atk()));
        txtDesc.setText(a.getSub_status());
        txtDescEfeito.setText(a.getEfeito());

        Usuario u = UserSessao.getUsuarioLogado();
        boolean isAdmin = (u != null && u.isAdmin());
        if (adminBotoesHBox != null) {
            adminBotoesHBox.setVisible(isAdmin);
            adminBotoesHBox.setManaged(isAdmin);
        }
    }

    public void setParentVBox(VBox parentVBox) {
        this.parentVBox = parentVBox;
    }

    @FXML
    void deletarArma(ActionEvent event) {
        boolean confirmou = AlertUtils.mostrarConfirmacao("Deletar", "Excluir arma " + arma.getNome() + "?", "Isso não pode ser desfeito.", rootPane.getScene().getWindow());
        if (confirmou) {
            try {
                armaDAO.deletarArma(arma.getId_arma());
                if (parentVBox != null) parentVBox.getChildren().remove(rootPane);
                AlertUtils.mostrarSucesso("Sucesso", "Arma deletada.");
            } catch (Exception e) {
                AlertUtils.mostrarErro("Erro", null, e.getMessage());
            }
        }
    }

    @FXML
    void editarArma(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminArma.fxml"));
            Node formNode = loader.load();

            AdminArmaController controller = loader.getController();
            controller.carregarParaEdicao(this.arma);

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
