package controller;

import dao.ArmaDAO;
import model.Arma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class AdminArmaController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTipoArma;
    @FXML
    private TextField txtEstrelas;
    @FXML
    private TextField txtBaseAtk;
    @FXML
    private TextField txtSubStatus;
    @FXML
    private TextField txtImagem;
    @FXML
    private TextArea txtEfeito;
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;

    private ArmaDAO armaDAO = new ArmaDAO();

    @FXML
    void salvarArma(ActionEvent event) {
        String nome = txtNome.getText();
        String tipoArma = txtTipoArma.getText();
        String estrelasStr = txtEstrelas.getText();
        String baseAtkStr = txtBaseAtk.getText();
        String subStatus = txtSubStatus.getText();
        String imagem = txtImagem.getText();
        String efeito = txtEfeito.getText();

        if (nome.isEmpty() || tipoArma.isEmpty() || estrelasStr.isEmpty() || baseAtkStr.isEmpty() || subStatus.isEmpty() || imagem.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Preencha todos os campos.");
            return;
        }

        try {
            int estrelas = Integer.parseInt(estrelasStr);
            int baseAtk = Integer.parseInt(baseAtkStr);

            Arma a = new Arma();
            a.setNome(nome);
            a.setTipo_arma(tipoArma);
            a.setEstrelas(estrelas);
            a.setBase_atk(baseAtk);
            a.setSub_status(subStatus);
            a.setEfeito(efeito);
            a.setImagem(imagem);

            armaDAO.inserirArma(a);

            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            lblMensagem.setText("Arma '" + nome + "' salva com sucesso!");

            // Limpa os campos
            txtNome.clear();
            txtTipoArma.clear();
            txtEstrelas.clear();
            txtBaseAtk.clear();
            txtSubStatus.clear();
            txtImagem.clear();
            txtEfeito.clear();

        } catch (NumberFormatException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: 'Estrelas' e 'Ataque Base' devem ser números.");
        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro inesperado: " + e.getMessage());
        }
    }
}
