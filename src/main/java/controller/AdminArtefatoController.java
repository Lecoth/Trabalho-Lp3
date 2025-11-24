package controller;

import dao.ArtefatoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Artefato;
import utils.AlertUtils;

import java.sql.SQLException;

public class AdminArtefatoController {
    @FXML private TextField txtNomeset;
    @FXML private TextField txtEstrelas;
    @FXML private TextField txtImagem;
    @FXML private TextArea txt2pecas;
    @FXML private TextArea txt4pecas;
    @FXML private Button btnSalvar;

    private ArtefatoDAO artefatoDAO = new ArtefatoDAO();
    private Artefato artefatoParaEditar = null;

    public void carregarParaEdicao(Artefato artefato) {
        this.artefatoParaEditar = artefato;
        btnSalvar.setText("Atualizar Artefato");

        txtNomeset.setText(artefato.getNome_set());
        txtEstrelas.setText(String.valueOf(artefato.getEstrelas()));
        txtImagem.setText(artefato.getImagem());
        txt2pecas.setText(artefato.getEfeito_2pecas());
        txt4pecas.setText(artefato.getEfeito_4pecas());
    }

    @FXML
    void salvarArtefato(ActionEvent event) {
        String nome = txtNomeset.getText();
        String estrelasStr = txtEstrelas.getText();
        String duasPecasStr = txt2pecas.getText();
        String quatroPecas = txt4pecas.getText();
        String imagem = txtImagem.getText();

        if (nome.isEmpty() || estrelasStr.isEmpty() || duasPecasStr.isEmpty() || quatroPecas.isEmpty()) {
            AlertUtils.mostrarErro("Erro", null, "Preencha todos os campos.");
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

            if (artefatoParaEditar == null) {
                artefatoDAO.inserirArtefato(a);
                AlertUtils.mostrarSucesso("Sucesso", "Artefato '" + nome + "' salvo!");
                limparCampos();
            } else {
                a.setId_artefato(artefatoParaEditar.getId_artefato());
                artefatoDAO.atualizarArtefato(a);
                AlertUtils.mostrarSucesso("Sucesso", "Artefato '" + nome + "' atualizado!");
                limparCampos();
            }

        } catch (NumberFormatException e) {
            AlertUtils.mostrarErro("Erro de Formato", null, "Estrelas deve ser um número.");
        } catch (SQLException e) {
            AlertUtils.mostrarErro("Erro de Banco", null, e.getMessage());
        } catch (Exception e) {
            AlertUtils.mostrarErro("Erro Inesperado", null, e.getMessage());
        }
    }

    private void limparCampos() {
        txtNomeset.clear();
        txtEstrelas.clear();
        txtImagem.clear();
        txt2pecas.clear();
        txt4pecas.clear();
        artefatoParaEditar = null;
        btnSalvar.setText("Salvar Artefato");
    }
}