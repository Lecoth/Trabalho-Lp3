package controller;

import dao.ArmaDAO;
import model.Arma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.AlertUtils;

import java.sql.SQLException;

public class AdminArmaController {

    @FXML private TextField txtNome;
    @FXML private TextField txtTipoArma;
    @FXML private TextField txtEstrelas;
    @FXML private TextField txtBaseAtk;
    @FXML private TextField txtSubStatus;
    @FXML private TextField txtImagem;
    @FXML private TextArea txtEfeito;
    @FXML private Button btnSalvar;

    private ArmaDAO armaDAO = new ArmaDAO();

    private Arma armaParaEditar = null;

    public void carregarParaEdicao(Arma arma) {
        this.armaParaEditar = arma;
        btnSalvar.setText("Atualizar Arma");

        txtNome.setText(arma.getNome());
        txtTipoArma.setText(arma.getTipo_arma());
        txtEstrelas.setText(String.valueOf(arma.getEstrelas()));
        txtBaseAtk.setText(String.valueOf(arma.getBase_atk()));
        txtSubStatus.setText(arma.getSub_status());
        txtImagem.setText(arma.getImagem());
        txtEfeito.setText(arma.getEfeito());
    }

    @FXML
    void salvarArma(ActionEvent event) {
        String nome = txtNome.getText();
        String tipoArma = txtTipoArma.getText();
        String estrelasStr = txtEstrelas.getText();
        String baseAtkStr = txtBaseAtk.getText();
        String subStatus = txtSubStatus.getText();
        String imagem = txtImagem.getText();
        String efeito = txtEfeito.getText();

        if (nome.isEmpty() || tipoArma.isEmpty() || estrelasStr.isEmpty() || baseAtkStr.isEmpty()) {
            AlertUtils.mostrarErro("Erro", null,"Preencha os campos obrigatórios.");
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

            if (armaParaEditar == null) {
                armaDAO.inserirArma(a);
                AlertUtils.mostrarSucesso("Sucesso", "Arma '" + nome + "' salva!");
                limparCampos();
            } else {
                a.setId_arma(armaParaEditar.getId_arma());
                armaDAO.atualizarArma(a);
                AlertUtils.mostrarSucesso("Sucesso", "Arma '" + nome + "' atualizada!");
            }

        } catch (NumberFormatException e) {
            AlertUtils.mostrarErro("Erro de Formato", null, "Estrelas e Ataque Base devem ser números.");
        } catch (SQLException e) {
            AlertUtils.mostrarErro("Erro de Banco", null, e.getMessage());
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro Inesperado", null, e.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtTipoArma.clear();
        txtEstrelas.clear();
        txtBaseAtk.clear();
        txtSubStatus.clear();
        txtImagem.clear();
        txtEfeito.clear();
        armaParaEditar = null;
        btnSalvar.setText("Salvar Arma");
    }
}