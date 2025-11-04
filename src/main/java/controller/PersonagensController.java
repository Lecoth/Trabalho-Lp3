package controller;

import dao.PersonagemDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import model.Personagem;

import java.io.IOException;
import java.util.List;

public class PersonagensController {

    @FXML
    private VBox vboxConteudo;

    private PersonagemDAO personagemDAO = new PersonagemDAO();

    @FXML
    public void initialize() {
        carregarPersonagens();
    }

    private void carregarPersonagens() {
        List<Personagem> listaDePersonagens = personagemDAO.buscarTodosPersonagens();

        vboxConteudo.getChildren().clear();

        for (Personagem p : listaDePersonagens) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/personagemMolde.fxml"));
                Node cardNode = loader.load();

                PersonagemCardController cardController = loader.getController();

                cardController.setPersonagem(p);

                vboxConteudo.getChildren().add(cardNode);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}