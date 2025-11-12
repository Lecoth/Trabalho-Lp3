package controller;

import dao.BuildGuiaDAO;
import db.UserSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TitledPane;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BuildGuia;
import model.BuildUserInfo;
import model.Usuario;
import dao.BuildUsuarioDAO;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML
    private BorderPane mainPane;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private VBox menuLateral;
    @FXML
    private TitledPane adminPane;

    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();
    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();

    @FXML
    public void initialize() throws IOException {
        carregarTela("home.fxml");

        Usuario usuarioLogado = UserSessao.getUsuarioLogado();

        if (usuarioLogado != null && usuarioLogado.isAdmin()) {
            adminPane.setVisible(true);
            adminPane.setManaged(true);
        }
    }
    @FXML
    private void toggleMenu(ActionEvent event) {
        boolean isVisible = menuLateral.isVisible();

        menuLateral.setVisible(!isVisible);
        menuLateral.setManaged(!isVisible);
    }

    @FXML
    private void pesquisarPersonagem(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")) {
            fazerPesquisa();
        }
    }

    @FXML
    private void iniciarPesquisaClick(ActionEvent event) throws IOException {
        fazerPesquisa();
    }

    private void fazerPesquisa() throws IOException {
        String termo = txtPesquisa.getText().trim();
        if (termo.isEmpty()) {
            carregarTela("home.fxml"); // Se a pesquisa for vazia, volta pra home
            return;
        }

        System.out.println("Pesquisando por: " + termo);

        BuildGuia buildGuiaEncontrada = buildGuiaDAO.buscarBuildGuiaPorPersonagem(termo);

        if (buildGuiaEncontrada != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/buildGuiaMolde.fxml"));
            Node buildView = loader.load();
            BuildCardController cardController = loader.getController();
            cardController.setBuild(buildGuiaEncontrada);
            mainPane.setCenter(buildView);

        } else {
            List<BuildUserInfo> buildsUsuarioEncontradas = buildUsuarioDAO.buscarBuildsPublicasPorNome(termo);

            if (buildsUsuarioEncontradas != null && !buildsUsuarioEncontradas.isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usersbuilds.fxml"));
                Node node = loader.load();

                UserBuildsController controller = loader.getController();
                controller.carregarBuildsPesquisadas(buildsUsuarioEncontradas);

                mainPane.setCenter(node);

            } else {
                System.out.println("Build não encontrada em Guias ou User Builds.");
                carregarTela("naoEncontrado.fxml");
            }
        }
    }

    private void carregarTela(String nomeFXML) throws IOException {

        Node node = FXMLLoader.load(getClass().getResource("/fxml/" + nomeFXML));

        mainPane.setCenter(node);
    }

    // Ações dos botões do menu
    @FXML
    private void abrirHome(ActionEvent event) throws IOException {
        carregarTela("home.fxml");
    }

    @FXML
    private void abrirPersonagens(ActionEvent event) throws IOException {
        carregarTela("personagens.fxml");
    }

    @FXML
    private void abrirBuilds(ActionEvent event) throws IOException {
        carregarTela("mybuild.fxml");
    }

    @FXML
    private void abrirPerfil(ActionEvent event) throws IOException {
        carregarTela("perfil.fxml");
    }

    @FXML
    private void abrirBuildsGuia(ActionEvent event) throws IOException {
        carregarTela("buildsguia.fxml");
    }

    @FXML
    private void abrirUsersBuilds(ActionEvent event) throws IOException {
        carregarTela("usersbuilds.fxml");
    }

    @FXML
    private void abrirArmas(ActionEvent event) throws IOException {
        carregarTela("armas.fxml");
    }

    @FXML
    private void abrirArtefatos(ActionEvent event) throws IOException {
        carregarTela("artefatos.fxml");
    }

    @FXML
    private void abrirAjuda(ActionEvent event) throws IOException {
        carregarTela("ajuda.fxml");
    }

    @FXML
    private void fazerLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) mainPane.getScene().getWindow();

        stage.setScene(scene);
    }

    @FXML
    private void abrirAdminPersonagens(ActionEvent event) throws IOException {
        carregarTela("adminPersonagem.fxml");
    }

    @FXML
    private void abrirAdminArmas(ActionEvent event) throws IOException {
        carregarTela("adminArma.fxml");
    }

    @FXML
    private void abrirAdminArtefatos(ActionEvent event) throws IOException {
        carregarTela("adminArtefato.fxml");
    }

    @FXML
    private void abrirAdminBuildGuia(ActionEvent event) throws IOException {
        carregarTela("adminBuildGuia.fxml");
    }

}