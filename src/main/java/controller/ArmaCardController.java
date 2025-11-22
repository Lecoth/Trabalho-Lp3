package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Arma;
import utils.ImageUtils;

public class ArmaCardController {
    @FXML
    private ImageView imgArma;
    @FXML
    private Label lblNomeArma;
    @FXML
    private Text txtAtk;
    @FXML
    private Text txtDesc;
    @FXML
    private  Text txtDescEfeito;

    public void setArma(Arma a){
        lblNomeArma.setText(a.getNome());
        imgArma.setImage(carregarImagemSegura(a.getImagem()));
        txtAtk.setText(String.valueOf(a.getBase_atk()));
        txtDesc.setText(a.getSub_status());
        txtDescEfeito.setText(a.getEfeito());
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}
