package controller;

import dao.ArtefatoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Artefato;

import java.sql.SQLException;

public class AdminArtefatoController {
    @FXML
    private TextField txtNomeset;
    @FXML
    private TextField txtEstrelas;
    @FXML
    private TextField txtImagem;
    @FXML
    private TextArea txt2pecas;
    @FXML
    private TextArea txt4pecas;
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;

    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();

    @FXML
    void salvarArtefato(ActionEvent event) {
        String nome = txtNomeset.getText();
        String estrelasStr = txtEstrelas.getText();
        String duasPecasStr = txt2pecas.getText();
        String quatroPecas = txt4pecas.getText();
        String imagem = txtImagem.getText();

        if (nome.isEmpty() || estrelasStr.isEmpty() || duasPecasStr.isEmpty() || quatroPecas.isEmpty() || imagem.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Preencha todos os campos.");
            return;
        }

        try {
            int estrelas = Integer.parseInt(estrelasStr);

            Artefato a = new Artefato();
            a.setNome_set(nome);
            a.setEstrelas(estrelas);
            a.setEfeito_2pecas(duasPecasStr);
            a.setEfeito_4pecas(quatroPecas);
            a.setImagem(imagem);

            artefatoDAO.inserirArtefato(a);

            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            lblMensagem.setText("Artefato '" + nome + "' salvo com sucesso!");

            // Limpa os campos
            txtNomeset.clear();
            txtEstrelas.clear();
            txtImagem.clear();
            txt2pecas.clear();
            txt4pecas.clear();

        } catch (NumberFormatException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: 'Estrelas' deve ser um número.");
        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro inesperado: " + e.getMessage());
        }
    }
}
