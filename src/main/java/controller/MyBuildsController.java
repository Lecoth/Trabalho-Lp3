package controller;

import dao.BuildUsuarioDAO;
import db.UserSessao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.BuildUserInfo;
import model.Usuario;

import java.io.IOException;
import java.util.List;

public class MyBuildsController {

    @FXML
    private VBox vboxConteudo;

    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();

    @FXML
    public void initialize() {
        carregarMinhasBuilds();
    }

    private void carregarMinhasBuilds() {
        Usuario usuarioLogado = UserSessao.getUsuarioLogado();
        if (usuarioLogado == null) {
            vboxConteudo.getChildren().clear();
            vboxConteudo.getChildren().add(new Label("Erro: Usuário não está logado."));
            return;
        }

        List<BuildUserInfo> builds = buildUsuarioDAO.buscarBuildsInfosPorUsuario(usuarioLogado.getIdUser());

        vboxConteudo.getChildren().clear(); // Limpa o VBox

        try {
            FXMLLoader loaderBotao = new FXMLLoader(getClass().getResource("/fxml/botaoCriarBuild.fxml"));
            Node botaoNode = loaderBotao.load();
            BotaoCriarBuildController botaoController = loaderBotao.getController();
            botaoController.setVboxConteudo(vboxConteudo);
            vboxConteudo.getChildren().add(botaoNode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (builds.isEmpty()) {
            Label lblAviso = new Label("Você ainda não criou nenhuma build.");
            lblAviso.setFont(new Font(18.0));
            Label lblSubAviso = new Label("Clique no botão acima para começar!");
            vboxConteudo.getChildren().addAll(lblAviso, lblSubAviso);
            return;
        }

        for (BuildUserInfo build : builds) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userBuildMolde.fxml"));
                Node cardNode = loader.load();

                UserBuildCardController cardController = loader.getController();
                cardController.setBuild(build);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}