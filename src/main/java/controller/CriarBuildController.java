package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CriarBuildController {

    @FXML
    private TextField txtNomeBuild;
    @FXML
    private ComboBox<String> comboPersonagem; // Usando sting em tudo por enquanto porque ainda não implementei essa parte.
    @FXML
    private ComboBox<String> comboArma;
    @FXML
    private ComboBox<String> comboArtefato;
    @FXML
    private ComboBox<String> comboSands;
    @FXML
    private ComboBox<String> comboGoblet;
    @FXML
    private ComboBox<String> comboCirclet;
    @FXML
    private CheckBox checkPrivada;
    @FXML
    private Button btnSalvar;

    @FXML
    public void initialize() {
        // Para a p2, popular os ComboBoxes aqui.
        // Usando os DAOs de Arma, Personagem e Artefato.

        // Por enquanto, vou só adicionar dados estáticos para você conseguir ver qual é a ideia.
        comboPersonagem.getItems().add("Personagem Exemplo 1");
        comboArma.getItems().add("Arma Exemplo 1");
        comboArtefato.getItems().add("Artefato Exemplo 1");
        comboSands.getItems().addAll("ATK%", "HP%", "DEF%", "Recarga de Energia%");
        comboGoblet.getItems().addAll("Bônus Pyro", "Bônus Hydro", "Bônus Físico");
        comboCirclet.getItems().addAll("Taxa Crítica", "Dano Crítico", "Bônus de Cura");
    }
}