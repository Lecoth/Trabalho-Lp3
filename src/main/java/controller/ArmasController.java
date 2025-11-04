package controller;

import dao.ArmaDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Arma;

import java.io.IOException;
import java.util.List;

public class ArmasController {
    @FXML
    private VBox vboxConteudo;

    private ArmaDAO armaDAO = new ArmaDAO();

    @FXML
    public void initialize() {
        carregarArmas();
    }

    private void carregarArmas() {
        List<Arma> listaDeArmas = armaDAO.buscarTodasArmas();

        vboxConteudo.getChildren().clear();

        for (Arma a : listaDeArmas) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/armaMolde.fxml"));
                Node cardNode = loader.load();

                ArmaCardController cardController = loader.getController();

                cardController.setArma(a);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
