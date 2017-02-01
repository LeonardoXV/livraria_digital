/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Victor
 */
public class EventoAdicionar implements EventHandler<ActionEvent>{

    
    public void handle(ActionEvent arg0){
        Parent root2 = null;
        try {
            root2 = FXMLLoader.load(getClass().getResource("AdicionarAoCarrinho.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventoAdicionar.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.show();
        System.out.println("Adicionado ao carrinho");
    }

    
}
