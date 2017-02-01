/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.aplicacao.CarrinhoDeCompras;
import br.com.fatec.livrariadigital.entidades.Livro;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class AdicionarAoCarrinho extends Application implements Initializable{
    
    @FXML
    Button btn_ok;
    
    @FXML
    private void handleok(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/AdicionarAoCarrinho.fxml"));
        
        Scene scene = new Scene(root);
        stage.setAlwaysOnTop(true);
        stage.setTitle("Datalhes do Livro");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}
