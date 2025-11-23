package controller;

import dao.ArmaDAO;
import dao.ArtefatoDAO;
import dao.BuildGuiaDAO;
import dao.PersonagemDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.Arma;
import model.Artefato;
import model.BuildGuia;
import model.Personagem;
import utils.ComboBoxUtils;

import java.sql.SQLException;
import java.util.List;

public class AdminBuildGuiaController {

    @FXML
    private ComboBox<Personagem> comboPersonagem;
    @FXML
    private ComboBox<Arma> comboArma;
    @FXML
    private ComboBox<Artefato> comboArtefato;
    @FXML
    private TextArea txtPqArmaIdeal;
    @FXML
    private TextField txtMainSands;
    @FXML
    private TextField txtMainGoblet;
    @FXML
    private TextField txtMainCirclet;
    @FXML
    private TextArea txtSubstats;
    @FXML
    private TextArea txtIdealStatus;
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;

    private PersonagemDAO personagemDAO = new PersonagemDAO();
    private ArmaDAO armaDAO = new ArmaDAO();
    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();
    private BuildGuia buildParaEditar;

    @FXML
    public void initialize() {
        carregarComboBoxes();
    }

    private void carregarComboBoxes() {
        ComboBoxUtils.configurarComboBoxes(
                comboPersonagem,
                comboArma,
                comboArtefato,
                personagemDAO,
                armaDAO,
                artefatoDAO
        );
    }

    public void carregarParaEdicao(BuildGuia build) {
        this.buildParaEditar = build;
        btnSalvar.setText("Atualizar Guia");

        for (Personagem p : comboPersonagem.getItems()) {
            if (p.getId_personagem() == build.getPersonagem().getId_personagem()) { comboPersonagem.setValue(p); break; }
        }
        for (Arma a : comboArma.getItems()) {
            if (a.getId_arma() == build.getArma().getId_arma()) { comboArma.setValue(a); break; }
        }
        for (Artefato art : comboArtefato.getItems()) {
            if (art.getId_artefato() == build.getArtefato().getId_artefato()) { comboArtefato.setValue(art); break; }
        }

        txtPqArmaIdeal.setText(build.getPq_arma_ideal());
        txtMainSands.setText(build.getMain_sands());
        txtMainGoblet.setText(build.getMain_goblet());
        txtMainCirclet.setText(build.getMain_circlet());
        txtSubstats.setText(build.getSubstatus());
        txtIdealStatus.setText(build.getIdeal_status());
    }

    @FXML
    void salvarBuildGuia(ActionEvent event) {
        try {
            Personagem p = comboPersonagem.getValue();
            Arma a = comboArma.getValue();
            Artefato art = comboArtefato.getValue();

            String pqArma = txtPqArmaIdeal.getText();
            String sands = txtMainSands.getText();
            String goblet = txtMainGoblet.getText();
            String circlet = txtMainCirclet.getText();
            String substats = txtSubstats.getText();
            String idealStatus = txtIdealStatus.getText();

            if (p == null || a == null || art == null || sands.isEmpty() || goblet.isEmpty() || circlet.isEmpty()) {
                lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
                lblMensagem.setText("Erro: Personagem, Arma, Artefato e Main Stats são obrigatórios.");
                return;
            }

            // Monta o objeto BuildGuia
            BuildGuia build = new BuildGuia();
            build.setPersonagem(p);
            build.setArma(a);
            build.setArtefato(art);
            build.setPq_arma_ideal(pqArma);
            build.setMain_sands(sands);
            build.setMain_goblet(goblet);
            build.setMain_circlet(circlet);
            build.setSubstatus(substats);
            build.setIdeal_status(idealStatus);

            if (buildParaEditar == null) {
                buildGuiaDAO.inserirBuildGuia(build);
                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Build Guia Salva!");
                limparCampos();
            } else {
                build.setId_build_guia(buildParaEditar.getId_build_guia());
                buildGuiaDAO.atualizarBuildGuia(build);
                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Atualizado com sucesso!");
            }

        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro inesperado: " + e.getMessage());
        }
    }

    private void limparCampos() {
        comboPersonagem.setValue(null);
        comboArma.setValue(null);
        comboArtefato.setValue(null);
        txtPqArmaIdeal.clear();
        txtMainSands.clear();
        txtMainGoblet.clear();
        txtMainCirclet.clear();
        txtSubstats.clear();
        txtIdealStatus.clear();
    }
}
