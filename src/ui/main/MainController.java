/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import util.AlgGenetico;

/**
 * FXML Controller class
 *
 * @author usuario.local
 */
public class MainController implements Initializable {

    @FXML
    private TextField txtPopulacao;
    @FXML
    private TextField txtTaxaCruzamento;
    @FXML
    private TextField txtTaxaMutacao;
    @FXML
    private TextField txtGeracoes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void executaAlgoritmo(ActionEvent event) {
        int populacao = Integer.valueOf(txtPopulacao.getText());
        int geracoes = Integer.valueOf(txtGeracoes.getText());
        float taxaMutacao = Float.valueOf(txtTaxaMutacao.getText()) / 100;
        float taxaCruzamento = Float.valueOf(txtTaxaCruzamento.getText()) / 100;
        AlgGenetico ag = new AlgGenetico(populacao, geracoes, taxaCruzamento, taxaMutacao);
        ag.executa();
    }
    
}
