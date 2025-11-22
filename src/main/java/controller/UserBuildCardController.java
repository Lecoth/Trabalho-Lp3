package controller;

import dao.BuildUsuarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.BuildUserInfo;
import utils.ImageUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UserBuildCardController {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView imgPersonagem;
    @FXML
    private ImageView imgArma;
    @FXML
    private ImageView imgArtefato;
    @FXML
    private Label lblNomeBuild;
    @FXML
    private Label lblNomePersonagem;
    @FXML
    private Label lblElemento;
    @FXML
    private Label lblNomeArma;
    @FXML
    private Label lblNomeArtefato;
    @FXML
    private Text txtSand;
    @FXML
    private Text txtGoblet;
    @FXML
    private Text txtCirclet;
    @FXML
    private Label lblStatusPrivacidade;
    @FXML
    private Label lblAutor;
    @FXML
    private Button btnEditar, btnDeletar;
    @FXML
    private HBox botoesHBox;
    @FXML
    private TextArea txtDescricao;

    private BuildUserInfo build;
    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();
    private VBox parentVBox;
    private boolean mostrarAutor = false;

    public void setBuild(BuildUserInfo build) {
        this.build = build;

        if (build == null) {
            System.out.println("Build nula recebida em UserBuildCardController");
            return;
        }

        lblNomeBuild.setText(build.getNome_build());
        lblNomePersonagem.setText(build.getPersonagem().getNome());
        lblElemento.setText(build.getPersonagem().getElemento());
        lblNomeArma.setText(build.getArma().getNome());
        lblNomeArtefato.setText(build.getArtefato().getNome_set());

        txtSand.setText(build.getSands_main());
        txtGoblet.setText(build.getGoblet_main());
        txtCirclet.setText(build.getCirclet_main());

        imgPersonagem.setImage(carregarImagemSegura(build.getPersonagem().getImagem()));
        imgArma.setImage(carregarImagemSegura(build.getArma().getImagem()));
        imgArtefato.setImage(carregarImagemSegura(build.getArtefato().getImagem()));

        if (build.isPrivada()) {
            lblStatusPrivacidade.setVisible(true);
        }

        // Mostra o autor se estiver na tela pública
        if (mostrarAutor) {
            lblAutor.setText("Criado por: " + build.getNome_usuario());
            lblAutor.setVisible(true);
            botoesHBox.setVisible(false);
            botoesHBox.setManaged(false);
        } else {
            lblAutor.setVisible(false);
            botoesHBox.setVisible(true);
            botoesHBox.setManaged(true);
        }

        if (build.getDescricao() != null && !build.getDescricao().isEmpty()) {
            txtDescricao.setText(build.getDescricao());
        } else {
            txtDescricao.setText("Sem descrição.");
        }
    }

    public void setMostrarAutor(boolean mostrar) {
        this.mostrarAutor = mostrar;
    }

    public void setParentVBox(VBox parentVBox) {
        this.parentVBox = parentVBox;
    }

    @FXML
    void deletarBuild(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Deletar Build: " + build.getNome_build());
        alert.setContentText("Você tem certeza que quer deletar esta build? Esta ação não pode ser desfeita.");
        javafx.stage.Stage stage = (javafx.stage.Stage) rootPane.getScene().getWindow();
        alert.initOwner(stage);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                buildUsuarioDAO.deletarBuildUsuario(build.getId_build_user());
                parentVBox.getChildren().remove(rootPane);
            } catch (SQLException e) {
                Alert erroAlert = new Alert(Alert.AlertType.ERROR);
                erroAlert.setTitle("Erro de SQL");
                erroAlert.setContentText("Não foi possível deletar a build: " + e.getMessage());
                erroAlert.showAndWait();
            }
        }
    }

    @FXML
    void editarBuild(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/criarBuild.fxml"));
            Node formNode = loader.load();

            CriarBuildController formController = loader.getController();

            formController.carregarParaEdicao(this.build);

            BorderPane mainPane = (BorderPane) rootPane.getScene().lookup("#mainPane");
            if (mainPane != null) {
                mainPane.setCenter(formNode);
            } else {
                System.out.println("Erro: Não foi possível encontrar o mainPane.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}