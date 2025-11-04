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

    public void setPersonagem(Personagem p) {
        lblNomePersonagem.setText(p.getNome());
        imgPersonagem.setImage(carregarImagemSegura(p.getImagem()));
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