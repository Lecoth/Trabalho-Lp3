package controller;

import dao.ArtefatoDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Artefato;

import java.io.IOException;
import java.util.List;

public class ArtefatosController {
    @FXML
    private VBox vboxConteudo;

    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();

    @FXML
    public void initialize() {
        carregarArtefatos();
    }

    private void carregarArtefatos() {
        List<Artefato> listaDeArtefatos = artefatoDAO.buscarTodosArtefatos();

        vboxConteudo.getChildren().clear();

        for (Artefato a : listaDeArtefatos) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/artefatoMolde.fxml"));
                Node cardNode = loader.load();

                ArtefatoCardController cardController = loader.getController();

                cardController.setArtefato(a);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
