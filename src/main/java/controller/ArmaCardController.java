package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Arma;

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
        if (caminhoDoBanco == null || caminhoDoBanco.isEmpty()) {
            System.out.println("Caminho de imagem vazio.");
            return null;
        }
        try {
            if (!caminhoDoBanco.startsWith("/")) {
                caminhoDoBanco = "/" + caminhoDoBanco;
            }
            Image img = new Image(getClass().getResourceAsStream(caminhoDoBanco));
            if (img.isError()) {
                System.out.println("Erro ao carregar imagem: " + caminhoDoBanco);
            }
            return img;
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem: " + caminhoDoBanco);
            e.printStackTrace();
            return null;
        }
    }
}
