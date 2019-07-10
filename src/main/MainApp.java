/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.main.MainController;
import util.AlgGenetico;

/**
 *
 * @author usuario.local
 */
public class MainApp extends Application {
    private MainController mainController;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Locale.setDefault(new Locale("pt", "BR"));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/main/Main.fxml"));
        mainController = (MainController) loader.getController();
        Parent root = loader.load();
        primaryStage.setTitle("Algoritmo Genético - Unidade de Saúde");
        primaryStage.setMaximized(false);
        Scene cena = new Scene(root);
        primaryStage.setScene(cena);
        primaryStage.show();
    }
    
}
