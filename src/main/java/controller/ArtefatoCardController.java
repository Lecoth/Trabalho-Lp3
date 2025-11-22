package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Artefato;

public class ArtefatoCardController {
    @FXML
    private ImageView imgArtefato;
    @FXML
    private Label lblNomeSet;
    @FXML
    private Text txt2pecas;
    @FXML
    private Text txt4pecas;

    public void setArtefato(Artefato a){
        lblNomeSet.setText(a.getNome_set());
        imgArtefato.setImage(carregarImagemSegura(a.getImagem()));
        txt2pecas.setText(a.getEfeito_2pecas());
        txt4pecas.setText(a.getEfeito_4pecas());
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        return utils.ImageUtils.carregarImagemSegura(getClass(), caminhoDoBanco);
    }
}
