package utils;

import dao.ArmaDAO;
import dao.ArtefatoDAO;
import dao.PersonagemDAO;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import model.Arma;
import model.Artefato;
import model.Personagem;

import java.util.List;

public class ComboBoxUtils {

    public static void configurarComboBoxes(
            ComboBox<Personagem> comboPersonagem,
            ComboBox<Arma> comboArma,
            ComboBox<Artefato> comboArtefato,
            PersonagemDAO personagemDAO,
            ArmaDAO armaDAO,
            ArtefatoDAO artefatoDAO
    ) {
        List<Personagem> personagens = personagemDAO.buscarTodosPersonagens();
        List<Artefato> artefatos = artefatoDAO.buscarTodosArtefatos();

        comboPersonagem.getItems().setAll(personagens);
        comboArtefato.getItems().setAll(artefatos);

        configurarConverterPersonagem(comboPersonagem);
        configurarConverterArma(comboArma);
        configurarConverterArtefato(comboArtefato);

        comboPersonagem.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null && newValue.getTipo_arma() != null) {
                List<Arma> armas = armaDAO.buscarArmasPorTipo(newValue.getTipo_arma());
                comboArma.getItems().setAll(armas);
            } else {
                comboArma.getItems().clear();
            }
        });
    }

    private static void configurarConverterPersonagem(ComboBox<Personagem> combo) {
        combo.setConverter(new StringConverter<Personagem>() {
            @Override public String toString(Personagem p) { return (p == null) ? null : p.getNome(); }
            @Override public Personagem fromString(String s) { return null; }
        });
    }

    private static void configurarConverterArma(ComboBox<Arma> combo) {
        combo.setConverter(new StringConverter<Arma>() {
            @Override public String toString(Arma a) { return (a == null) ? null : a.getNome(); }
            @Override public Arma fromString(String s) { return null; }
        });
    }

    private static void configurarConverterArtefato(ComboBox<Artefato> combo) {
        combo.setConverter(new StringConverter<Artefato>() {
            @Override public String toString(Artefato a) { return (a == null) ? null : a.getNome_set(); }
            @Override public Artefato fromString(String s) { return null; }
        });
    }
}
