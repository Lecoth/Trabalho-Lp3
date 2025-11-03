package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BuildGuia;

public class BuildCardController {

    @FXML
    private ImageView imgPersonagem;

    @FXML
    private ImageView imgArma;

    @FXML
    private ImageView imgArtefato;

    @FXML
    private Label lblNomePersonagem;

    @FXML
    private Label lblNomeArma;

    @FXML
    private Label lblNomeArtefato;

    @FXML
    private Text txtSand;

    @FXML
    private Text txtGoblet;

    @FXML
    private Text txtCirclet;

    @FXML
    private Label lblSubStatus;

    @FXML
    private Text txtSubstatusDesc;

    private BuildGuia build;

    public void setBuild(BuildGuia build) {
        this.build = build;

        if (build == null) {
            System.out.println("Build nula recebida em BuildCardController");
            return;
        }

        lblNomePersonagem.setText(build.getPersonagem().getNome());
        lblNomeArma.setText(build.getArma().getNome());
        lblNomeArtefato.setText(build.getArtefato().getNome_set());

        imgPersonagem.setImage(carregarImagemSegura(build.getPersonagem().getImagem()));
        imgArma.setImage(carregarImagemSegura(build.getArma().getImagem()));
        imgArtefato.setImage(carregarImagemSegura(build.getArtefato().getImagem()));

        txtSand.setText(build.getMain_sands());
        txtGoblet.setText(build.getMain_goblet());
        txtCirclet.setText(build.getMain_circlet());

        txtSubstatusDesc.setText(build.getSubstatus());
    }

    private Image carregarImagemSegura(String caminhoDoBanco) {
        if (caminhoDoBanco == null || caminhoDoBanco.isEmpty()) {
            System.out.println("Caminho de imagem vazio.");
            return null;
        }

        try {
            if (caminhoDoBanco.startsWith("http")) {
                return new Image(caminhoDoBanco, true);
            }

            if (caminhoDoBanco.contains(":") || caminhoDoBanco.startsWith("file:")) {
                if (!caminhoDoBanco.startsWith("file:")) {
                    caminhoDoBanco = "file:" + caminhoDoBanco;
                }
                return new Image(caminhoDoBanco, true);
            }

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
