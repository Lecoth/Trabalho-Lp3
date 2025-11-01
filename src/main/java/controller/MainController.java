package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private VBox menuLateral;

    @FXML
    public void initialize() throws IOException {
        carregarTela("home.fxml"); // Tela inicial padrão
    }
    @FXML
    private void toggleMenu(ActionEvent event) {
        // Pega o estado atual
        boolean isVisible = menuLateral.isVisible();

        // Inverte se está visível ou não
        menuLateral.setVisible(!isVisible);
        menuLateral.setManaged(!isVisible);
    }

    @FXML
    private void pesquisarPersonagem(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")) {
            fazerPesquisa(); // Chama o novo método
        }
    }

    @FXML
    private void iniciarPesquisaClick(ActionEvent event) throws IOException {
        fazerPesquisa(); // O botão também chama o método
    }

    // Método da pesquis
    private void fazerPesquisa() throws IOException {
        String termo = txtPesquisa.getText();
        System.out.println("Pesquisando: " + termo);

        // Coisas que tenho que fazer aqui depois:
        // 1. Chamar o banco de dados com o "termo".
        // 2. Carregar um FXML de "resultados.fxml".
        // 3. carregarTela("resultados.fxml"); (ou algo parecido)
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
    private void fazerLogout(ActionEvent event) throws IOException {
        // Carrega login
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) mainPane.getScene().getWindow();

        // Coloca a tela de login
        stage.setScene(scene);
    }
    // Preciso adicionar métodos para os botões restantes
    // (Favoritos, Sair, BuildsGuias, UsersBuilds, Artefatos, Armas)
}