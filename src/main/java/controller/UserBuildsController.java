package controller;

import dao.BuildUsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.BuildUserInfo;

import java.io.IOException;
import java.util.List;

public class UserBuildsController {

    @FXML
    private VBox vboxConteudo;
    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();

    @FXML
    public void initialize() {
        carregarTodasBuildsPublicas();
    }

    public void carregarTodasBuildsPublicas() {
        List<BuildUserInfo> builds = buildUsuarioDAO.buscarBuildsInfosPublicas();
        preencherVBox(builds, "Nenhuma build pública de usuário foi encontrada.", "Seja o primeiro a criar uma!");
    }

    public void carregarBuildsPesquisadas(List<BuildUserInfo> builds) {
        preencherVBox(builds, "Nenhum resultado encontrado para sua busca.", "");
    }

    public void exibirResultadosMistos(List<Node> cardsEncontrados) {
        vboxConteudo.getChildren().clear();

        if (cardsEncontrados.isEmpty()) {
            Label lblAviso = new Label("Nenhum resultado encontrado.");
            lblAviso.setFont(new Font(18.0));
            vboxConteudo.getChildren().add(lblAviso);
        } else {
            vboxConteudo.getChildren().addAll(cardsEncontrados);
        }
    }

    // Privado para preencher o VBox e evitar duplicação de código
    private void preencherVBox(List<BuildUserInfo> builds, String msgVazio, String msgSubAviso) {
        vboxConteudo.getChildren().clear();

        if (builds == null || builds.isEmpty()) {
            Label lblAviso = new Label(msgVazio);
            lblAviso.setFont(new Font(18.0));
            Label lblSubAviso = new Label(msgSubAviso);
            vboxConteudo.getChildren().addAll(lblAviso, lblSubAviso);
            return;
        }

        for (BuildUserInfo build : builds) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userBuildMolde.fxml"));
                Node cardNode = loader.load();
                UserBuildCardController cardController = loader.getController();

                cardController.setMostrarAutor(true); // Sempre mostra o autor
                cardController.setBuild(build);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}