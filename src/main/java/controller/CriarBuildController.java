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
import model.Arma;
import model.Artefato;
import model.BuildUsuario;
import model.Personagem;
import model.Usuario;

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

    // DAOs
    private PersonagemDAO personagemDAO = new PersonagemDAO();
    private ArmaDAO armaDAO = new ArmaDAO();
    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private BuildUsuarioDAO buildUsuarioDAO = new BuildUsuarioDAO(); // Novo DAO

    @FXML
    public void initialize() {
        // Carrega os dados dinâmicos do banco
        carregarComboBoxes();

        // Carrega os dados estáticos dos main stats
        comboSands.getItems().addAll("ATK%", "HP%", "DEF%", "Recarga de Energia%", "Proficiência Elemental");
        comboGoblet.getItems().addAll("Bônus Pyro", "Bônus Hydro", "Bônus Electro", "Bônus Cryo", "Bônus Anemo", "Bônus Geo", "Bônus Dendro", "Bônus Físico", "ATK%", "HP%", "DEF%", "Proficiência Elemental");
        comboCirclet.getItems().addAll("Taxa Crítica", "Dano Crítico", "Bônus de Cura", "ATK%", "HP%", "DEF%", "Proficiência Elemental");
    }

    /**
     * Carrega os ComboBoxes de Personagem, Arma e Artefato com dados do banco.
     * (Lógica reutilizada do AdminBuildGuiaController)
     */
    private void carregarComboBoxes() {
        List<Personagem> personagens = personagemDAO.buscarTodosPersonagens();
        List<Arma> armas = armaDAO.buscarTodasArmas();
        List<Artefato> artefatos = artefatoDAO.buscarTodosArtefatos();

        comboPersonagem.getItems().setAll(personagens);
        comboArma.getItems().setAll(armas);
        comboArtefato.getItems().setAll(artefatos);

        // Converte os objetos para texto para mostrar o nome
        comboPersonagem.setConverter(new StringConverter<Personagem>() {
            @Override
            public String toString(Personagem p) {
                return (p == null) ? null : p.getNome();
            }
            @Override
            public Personagem fromString(String string) { return null; }
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
    void salvarBuild(ActionEvent event) {
        // Pegar o usuário logado
        Usuario usuarioLogado = UserSessao.getUsuarioLogado();
        if (usuarioLogado == null) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Você não está logado!");
            return;
        }

        try {
            Personagem p = comboPersonagem.getValue();
            Arma a = comboArma.getValue();
            Artefato art = comboArtefato.getValue();

            String nomeBuild = txtNomeBuild.getText();
            String sands = comboSands.getValue();
            String goblet = comboGoblet.getValue();
            String circlet = comboCirclet.getValue();

            boolean isPrivada = checkPrivada.isSelected();

            if (p == null || a == null || art == null || nomeBuild.isEmpty() || sands == null || goblet == null || circlet == null) {
                lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
                lblMensagem.setText("Erro: Preencha todos os campos obrigatórios.");
                return;
            }

            // Montar o objeto BuildUsuario
            BuildUsuario build = new BuildUsuario();
            build.setId_usuario(usuarioLogado.getIdUser()); // id do usuário logado
            build.setId_personagem(p.getId_personagem());   // id do personagem
            build.setId_arma_usada(a.getId_arma());         // id da arma
            build.setId_art_set(art.getId_artefato());      // id do artefato
            build.setNome_build(nomeBuild);
            build.setPrivada(isPrivada);
            build.setSands_main(sands);
            build.setGoblet_main(goblet);
            build.setCirclet_main(circlet);

            // Salvar no banco
            buildUsuarioDAO.inserirBuildUsuario(build);

            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            lblMensagem.setText("Build '" + nomeBuild + "' salva com sucesso!");

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