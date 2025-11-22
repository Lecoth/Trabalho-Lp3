package controller;

import dao.BuildGuiaDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.BuildGuia;

import java.io.IOException;
import java.util.List;

public class BuildsGuiaController {
    @FXML
    private VBox vboxConteudo;

    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();

    @FXML
    public void initialize() {
        carregarBuilds();
    }

    private void carregarBuilds() {
        // Busca todas as builds do banco
        List<BuildGuia> listaDeBuilds = buildGuiaDAO.buscarTodasBuildsGuia();

        vboxConteudo.getChildren().clear();

        for (BuildGuia build : listaDeBuilds) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/buildGuiaMolde.fxml"));
                Node cardNode = loader.load();

                BuildCardController cardController = loader.getController();
                cardController.setParentVBox(vboxConteudo);
                cardController.setBuild(build);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
