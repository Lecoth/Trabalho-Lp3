package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MyBuildsController {

    @FXML
    private VBox vboxConteudo;

    @FXML
    public void initialize() {
        // Na p2, vou carregar as builds do usuário aqui.
        // Se a lista estiver vazia, mostra o aviso.
        // Por enquanto, a lista está sempre vazia.
    }

    @FXML
    private void abrirFormularioCriarBuild(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/criarBuild.fxml"));
            Node formNode = loader.load();

            BorderPane mainPane = (BorderPane) vboxConteudo.getScene().lookup("#mainPane");

            if (mainPane != null) {
                mainPane.setCenter(formNode);
            } else {
                System.out.println("Erro: Não foi possível encontrar o mainPane.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}