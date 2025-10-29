package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField txtPesquisa;

    @FXML
    public void initialize() throws IOException {
        carregarTela("home.fxml"); // Tela inicial padrão
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
    private void pesquisarPersonagem(KeyEvent event) throws IOException {
        if (event.getCode().toString().equals("ENTER")) {
            String termo = txtPesquisa.getText();
            System.out.println("Pesquisando: " + termo);
        }
    }

    // Preciso adicionar métodos para os botões restantes
    // (Favoritos, Sair, BuildsGuias, UsersBuilds, Artefatos, Armas)
}