package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.BuildUserInfo;

public class UserBuildCardController {

    @FXML
    private ImageView imgPersonagem;
    @FXML
    private ImageView imgArma;
    @FXML
    private ImageView imgArtefato;
    @FXML
    private Label lblNomeBuild; // Título
    @FXML
    private Label lblNomePersonagem;
    @FXML
    private Label lblElemento;
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
    private Label lblStatusPrivacidade;
    @FXML
    private Label lblAutor;

    // Flag para saber se esta na tela My Builds ou User Builds
    private boolean mostrarAutor = false;

    public void setBuild(BuildUserInfo build) {
        if (build == null) {
            System.out.println("Build nula recebida em UserBuildCardController");
            return;
        }

        lblNomeBuild.setText(build.getNome_build());
        lblNomePersonagem.setText(build.getPersonagem().getNome());
        lblElemento.setText(build.getPersonagem().getElemento());
        lblNomeArma.setText(build.getArma().getNome());
        lblNomeArtefato.setText(build.getArtefato().getNome_set());

        txtSand.setText(build.getSands_main());
        txtGoblet.setText(build.getGoblet_main());
        txtCirclet.setText(build.getCirclet_main());

        imgPersonagem.setImage(carregarImagemSegura(build.getPersonagem().getImagem()));
        imgArma.setImage(carregarImagemSegura(build.getArma().getImagem()));
        imgArtefato.setImage(carregarImagemSegura(build.getArtefato().getImagem()));

        if (build.isPrivada()) {
            lblStatusPrivacidade.setVisible(true);
        }

        // Mostra o autor se estiver na tela pública
        if (mostrarAutor) {
            lblAutor.setText("Criado por: " + build.getNome_usuario());
            lblAutor.setVisible(true);
        }
    }

    /**
     * Chame isso ANTES de setBuild() se quiser mostrar o nome do autor.
     */
    public void setMostrarAutor(boolean mostrar) {
        this.mostrarAutor = mostrar;
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
