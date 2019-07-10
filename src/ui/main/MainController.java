/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
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
    @FXML
    private TextField txtPosX;
    @FXML
    private TextField txtPosY;
    @FXML
    private TextField txtAptidao;
    @FXML
    private TextField txtGeracao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void executaAlgoritmo(ActionEvent event) throws ParseException {
        NumberFormat nf = NumberFormat.getInstance();
        int populacao = Integer.valueOf(txtPopulacao.getText());
        int geracoes = Integer.valueOf(txtGeracoes.getText());
        float taxaMutacao = nf.parse(txtTaxaMutacao.getText()).floatValue() / 100;
        float taxaCruzamento = nf.parse(txtTaxaCruzamento.getText()).floatValue() / 100;
        //Inicializa o Algoritmo Genético
        AlgGenetico ag = new AlgGenetico(populacao, geracoes, taxaCruzamento, taxaMutacao, this);
        //Executa o Algoritmo Genético
        ag.executa();
    }
    
    public void setResultados(double x, double y, double aptidao, int geracao) {
        txtPosX.setText(String.format("%.6f", x));
        txtPosY.setText(String.format("%.6f", y));
        txtAptidao.setText(String.format("%.8f", aptidao));
        txtGeracao.setText(String.valueOf(geracao));
    }
    
}
