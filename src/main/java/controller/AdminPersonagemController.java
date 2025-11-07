package controller;

import dao.PersonagemDAO;
import model.Personagem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class AdminPersonagemController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtElemento;
    @FXML
    private TextField txtTipoArma;
    @FXML
    private TextField txtEstrelas;
    @FXML
    private TextField txtImagem;
    @FXML
    private TextArea txtTalentos;
    @FXML
    private Label lblMensagem;
    @FXML
    private Button btnSalvar;

    private PersonagemDAO personagemDAO = new PersonagemDAO();

    @FXML
    void salvarPersonagem(ActionEvent event) {
        String nome = txtNome.getText();
        String elemento = txtElemento.getText();
        String tipoArma = txtTipoArma.getText();
        String estrelasStr = txtEstrelas.getText();
        String imagem = txtImagem.getText();
        String talentos = txtTalentos.getText();

        // Validação simples
        if (nome.isEmpty() || elemento.isEmpty() || tipoArma.isEmpty() || estrelasStr.isEmpty() || imagem.isEmpty()) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: Preencha todos os campos obrigatórios.");
            return;
        }

        try {
            int estrelas = Integer.parseInt(estrelasStr);

            // Cria o objeto Personagem
            Personagem p = new Personagem();
            p.setNome(nome);
            p.setElemento(elemento);
            p.setTipo_arma(tipoArma);
            p.setEstrelas(estrelas);
            p.setTalentos(talentos);
            p.setImagem(imagem);

            // Salva no banco de dados
            personagemDAO.inserirPersonagem(p);

            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            lblMensagem.setText("Personagem '" + nome + "' salvo com sucesso!");

            // Limpa os campos após salvar
            txtNome.clear();
            txtElemento.clear();
            txtTipoArma.clear();
            txtEstrelas.clear();
            txtImagem.clear();
            txtTalentos.clear();

        } catch (NumberFormatException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro: 'Estrelas' deve ser um número (4 ou 5).");
        } catch (SQLException e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro de SQL: " + e.getMessage());
        } catch (Exception e) {
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            lblMensagem.setText("Erro inesperado: " + e.getMessage());
        }
    }
}