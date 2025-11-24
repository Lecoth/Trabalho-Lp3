package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Personagem;
import utils.ImageUtils;

public class PersonagemCardController {

    @FXML
    private ImageView imgPersonagem;
    @FXML
    private Label lblNomePersonagem;
    @FXML
    private Label lblElemento;
    @FXML private Label lblArma;
    @FXML private Label lblEstrelas;

    public void setPersonagem(Personagem p) {
        lblNomePersonagem.setText(p.getNome());
        lblElemento.setText(p.getElemento());
        lblArma.setText(p.getTipo_arma());

        StringBuilder estrelasTexto = new StringBuilder();
        for (int i = 0; i < p.getEstrelas(); i++) {
            estrelasTexto.append("★");
        }
        lblEstrelas.setText(estrelasTexto.toString());

        imgPersonagem.setImage(ImageUtils.carregarImagemSegura(getClass(), p.getImagem()));
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}