package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Personagem;

public class PersonagemCardController {

    @FXML
    private ImageView imgPersonagem;
    @FXML
    private Label lblNomePersonagem;
    @FXML
    private Label lblElemento;

    public void setPersonagem(Personagem p) {
        lblNomePersonagem.setText(p.getNome());
        imgPersonagem.setImage(carregarImagemSegura(p.getImagem()));
        lblElemento.setText(p.getElemento());
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}