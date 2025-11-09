package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class BotaoCriarBuildController {

    private VBox vboxConteudo;

    public void setVboxConteudo(VBox vbox) {
        this.vboxConteudo = vbox;
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