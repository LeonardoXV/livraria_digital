/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.teste;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author r2d2
 */
public class TelaDePedidos extends Application implements Initializable {
  
    @FXML private TableColumn numero,data,valor,preco;
    @FXML private TableView tabela;
   
    private ObservableList<teste> options=FXCollections.observableArrayList(new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"),
            new teste("numero","data","valor","preço"));
    @Override
    public void start(Stage primaryStage) throws IOException {

       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDePedidos.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelasCadastrados.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Tela De Perfil");
       primaryStage.setFullScreen(true);
       primaryStage.show();
       
    }
      
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       /* this.data.setCellValueFactory(new PropertyValueFactory<teste,String>("data"));
        this.numero.setCellValueFactory(new PropertyValueFactory<teste,String>("numero"));
        this.preco.setCellValueFactory(new PropertyValueFactory<teste,String>("preco"));
        this.valor.setCellValueFactory(new PropertyValueFactory<teste,String>("valor"));
        this.tabela.setItems(options);*/
       
    }
    
}
