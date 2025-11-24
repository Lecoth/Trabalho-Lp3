package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import utils.AlertUtils;

public class AjudaController {

    @FXML
    private TextArea txtAjuda;

    @FXML
    void enviarAjuda(ActionEvent event) {
        String texto = txtAjuda.getText();

        if (texto.isEmpty()) {
            AlertUtils.mostrarErro("Atenção", null, "Por favor, descreva seu problema antes de enviar.");
            return;
        }

        AlertUtils.mostrarSucesso("Recebido!", "Sua mensagem foi enviada para o suporte. Entraremos em contato em breve.");

        txtAjuda.clear();
    }
}