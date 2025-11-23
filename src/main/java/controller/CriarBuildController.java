package controller;

import dao.ArmaDAO;
import dao.ArtefatoDAO;
import dao.BuildUsuarioDAO;
import dao.PersonagemDAO;
import db.UserSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import model.*;
import utils.AlertUtils;
import utils.ComboBoxUtils;

import java.sql.SQLException;
import java.util.List;

public class CriarBuildController {

    @FXML
    private TextField txtNomeBuild;
    @FXML
    private CheckBox checkPrivada;
    @FXML
    private Button btnSalvar;
    @FXML
    private Label lblMensagem;
    @FXML
    private ComboBox<Personagem> comboPersonagem;
    @FXML
    private ComboBox<Arma> comboArma;
    @FXML
    private ComboBox<Artefato> comboArtefato;
    @FXML
    private ComboBox<String> comboSands;
    @FXML
    private ComboBox<String> comboGoblet;
    @FXML
    private ComboBox<String> comboCirclet;
    @FXML
    private TextArea txtDescricao;

    private PersonagemDAO personagemDAO = new PersonagemDAO();
    private ArmaDAO armaDAO = new ArmaDAO();
    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO();

    private BuildUserInfo buildParaEditar = null;

    @FXML
    public void initialize() {
        carregarComboBoxes();
        carregarStatsEstaticos();
    }

    public void carregarParaEdicao(BuildUserInfo build) {
        this.buildParaEditar = build;
        btnSalvar.setText("Atualizar Build");

        txtNomeBuild.setText(build.getNome_build());
        checkPrivada.setSelected(build.isPrivada());
        txtDescricao.setText(build.getDescricao());

        for (Personagem p : comboPersonagem.getItems()) {
            if (p.getId_personagem() == build.getPersonagem().getId_personagem()) {
                comboPersonagem.setValue(p);
                break;
            }
        }
        for (Arma a : comboArma.getItems()) {
            if (a.getId_arma() == build.getArma().getId_arma()) {
                comboArma.setValue(a);
                break;
            }
        }
        for (Artefato art : comboArtefato.getItems()) {
            if (art.getId_artefato() == build.getArtefato().getId_artefato()) {
                comboArtefato.setValue(art);
                break;
            }
        }

        comboSands.setValue(build.getSands_main());
        comboGoblet.setValue(build.getGoblet_main());
        comboCirclet.setValue(build.getCirclet_main());
    }

    @FXML
    void salvarBuild(ActionEvent event) {
        Usuario usuarioLogado = UserSessao.getUsuarioLogado();
        if (usuarioLogado == null) { /* ... (mensagem de erro) ... */ return; }

        try {
            Personagem p = comboPersonagem.getValue();
            Arma a = comboArma.getValue();
            Artefato art = comboArtefato.getValue();
            String nomeBuild = txtNomeBuild.getText();
            String sands = comboSands.getValue();
            String goblet = comboGoblet.getValue();
            String circlet = comboCirclet.getValue();
            boolean isPrivada = checkPrivada.isSelected();
            String descricao = txtDescricao.getText();

            if (p == null || a == null || art == null || nomeBuild.isEmpty() || sands == null || goblet == null || circlet == null) {
                AlertUtils.mostrarErro("Atenção", null, "Preencha todos os campos obrigatórios.");
                return;
            }

            BuildUsuario build = new BuildUsuario();
            build.setId_usuario(usuarioLogado.getIdUser());
            build.setId_personagem(p.getId_personagem());
            build.setId_arma_usada(a.getId_arma());
            build.setId_art_set(art.getId_artefato());
            build.setNome_build(nomeBuild);
            build.setPrivada(isPrivada);
            build.setSands_main(sands);
            build.setGoblet_main(goblet);
            build.setCirclet_main(circlet);
            build.setDescricao(descricao);

            if (buildParaEditar == null) {
                buildUsuarioDAO.inserirBuildUsuario(build);
                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Build '" + nomeBuild + "' salva com sucesso!");
                limparCampos();
            } else {
                build.setId_build_user(buildParaEditar.getId_build_user());
                buildUsuarioDAO.atualizarBuildUsuario(build);
                lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
                lblMensagem.setText("Build '" + nomeBuild + "' ATUALIZADA com sucesso!");
            }

        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro inesperado: " + e.getMessage());
        }
    }

    private void carregarStatsEstaticos() {
        comboSands.getItems().addAll("ATK%", "HP%", "DEF%", "Recarga de Energia%", "Proficiência Elemental");
        comboGoblet.getItems().addAll("Bônus Pyro", "Bônus Hydro", "Bônus Electro", "Bônus Cryo", "Bônus Anemo", "Bônus Geo", "Bônus Dendro", "Bônus Físico", "ATK%", "HP%", "DEF%", "Proficiência Elemental");
        comboCirclet.getItems().addAll("Taxa Crítica", "Dano Crítico", "Bônus de Cura", "ATK%", "HP%", "DEF%", "Proficiência Elemental");
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

    private void limparCampos() {
        txtNomeBuild.clear();
        comboPersonagem.setValue(null);
        comboArma.setValue(null);
        comboArtefato.setValue(null);
        comboSands.setValue(null);
        comboGoblet.setValue(null);
        comboCirclet.setValue(null);
        checkPrivada.setSelected(false);
    }
}