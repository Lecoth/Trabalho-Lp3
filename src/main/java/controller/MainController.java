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

        txtPesquisa.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 3 || newValue.isEmpty()) {
                try {
                    fazerPesquisa();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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

        // Se estiver vazio, volta para a Home
        if (termo.isEmpty()) {
            carregarTela("home.fxml");
            return;
        }

        System.out.println("Pesquisando (Mista) por: " + termo);

        java.util.List<Node> cardsEncontrados = new java.util.ArrayList<>();

        List<BuildGuia> guias = buildGuiaDAO.buscarBuildGuiaPorPersonagem(termo);

        if (guias != null) {
            for (BuildGuia g : guias) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/buildGuiaMolde.fxml"));
                Node cardGuia = loader.load();
                BuildCardController controller = loader.getController();
                controller.setBuild(g);

                // Adiciona na lista de achados
                cardsEncontrados.add(cardGuia);
            }
        }

        List<model.BuildUserInfo> userBuilds = buildUsuarioDAO.buscarBuildsPublicasPorNome(termo);

        if (userBuilds != null) {
            for (model.BuildUserInfo ub : userBuilds) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userBuildMolde.fxml"));
                Node cardNode = loader.load();
                UserBuildCardController controller = loader.getController();
                controller.setMostrarAutor(true);
                controller.setBuild(ub);
                cardsEncontrados.add(cardNode);
            }
        }

        if (cardsEncontrados.isEmpty()) {
            carregarTela("naoEncontrado.fxml");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/usersbuilds.fxml"));
            Node listaNode = loader.load();

            UserBuildsController listaController = loader.getController();
            listaController.exibirResultadosMistos(cardsEncontrados);

            mainPane.setCenter(listaNode);
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