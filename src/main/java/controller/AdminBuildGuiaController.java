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

import java.sql.SQLException;
import java.util.List;

public class AdminBuildGuiaController {

    // ComboBoxes (Note que eles guardam o OBJETO, não só o texto)
    @FXML
    private ComboBox<Personagem> comboPersonagem;
    @FXML
    private ComboBox<Arma> comboArma;
    @FXML
    private ComboBox<Artefato> comboArtefato;

    // Campos de Texto
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

    // Outros
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;

    // DAOs
    private PersonagemDAO personagemDAO = new PersonagemDAO();
    private ArmaDAO armaDAO = new ArmaDAO();
    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private BuildGuiaDAO buildGuiaDAO = new BuildGuiaDAO();

    @FXML
    public void initialize() {
        // Este método é chamado assim que a tela é carregada
        carregarComboBoxes();
    }

    private void carregarComboBoxes() {
        // 1. Carrega os dados do banco
        List<Personagem> personagens = personagemDAO.buscarTodosPersonagens();
        List<Arma> armas = armaDAO.buscarTodasArmas();
        List<Artefato> artefatos = artefatoDAO.buscarTodosArtefatos();

        // 2. Joga as listas nos ComboBoxes
        comboPersonagem.getItems().setAll(personagens);
        comboArma.getItems().setAll(armas);
        comboArtefato.getItems().setAll(artefatos);

        // 3. Converte os objetos para texto (para mostrar o nome)
        // Sem isso, o ComboBox mostraria "model.Personagem@123abc"

        comboPersonagem.setConverter(new StringConverter<Personagem>() {
            @Override
            public String toString(Personagem p) {
                return (p == null) ? null : p.getNome();
            }
            @Override
            public Personagem fromString(String string) { return null; } // Não precisamos disso
        });

        comboArma.setConverter(new StringConverter<Arma>() {
            @Override
            public String toString(Arma a) {
                return (a == null) ? null : a.getNome();
            }
            @Override
            public Arma fromString(String string) { return null; }
        });

        comboArtefato.setConverter(new StringConverter<Artefato>() {
            @Override
            public String toString(Artefato art) {
                return (art == null) ? null : art.getNome_set();
            }
            @Override
            public Artefato fromString(String string) { return null; }
        });
    }

    @FXML
    void salvarBuildGuia(ActionEvent event) {
        try {
            // 1. Pega os OBJETOS selecionados
            Personagem p = comboPersonagem.getValue();
            Arma a = comboArma.getValue();
            Artefato art = comboArtefato.getValue();

            // 2. Pega os Textos
            String pqArma = txtPqArmaIdeal.getText();
            String sands = txtMainSands.getText();
            String goblet = txtMainGoblet.getText();
            String circlet = txtMainCirclet.getText();
            String substats = txtSubstats.getText();
            String idealStatus = txtIdealStatus.getText();

            // 3. Validação
            if (p == null || a == null || art == null || sands.isEmpty() || goblet.isEmpty() || circlet.isEmpty()) {
                lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
                lblMensagem.setText("Erro: Personagem, Arma, Artefato e Main Stats são obrigatórios.");
                return;
            }

            // 4. Monta o objeto BuildGuia
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

            // 5. Salva no banco
            buildGuiaDAO.inserirBuildGuia(build);

            // 6. Sucesso
            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            lblMensagem.setText("Build Guia para '" + p.getNome() + "' salva com sucesso!");

            // 7. Limpa os campos
            limparCampos();

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
