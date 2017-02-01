/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Pedido;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosCadastradosController;
import br.com.fatec.livrariadigital.telasgui.controllers.PedidosCadastradosController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;

/**
 *
 * @author Leonardo
 */
public class TelaDePedidosCadastrados extends Application implements Initializable {
 @FXML private ListView<String> lista;

   private  List<Pedido>pedidos=new ArrayList();
    private final PedidosCadastradosController controller=new PedidosCadastradosController();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDePedidosCadastrados.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelasCadastrados.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Tela De Autores");
       primaryStage.setFullScreen(true);
       primaryStage.show();
       
    }
      
    public static void main(String[] args) {
        launch(args);
    }
    
   
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       this.controller.preencherListView(this.lista);
    }
    

    
   @FXML
    public void handlerNovoLivro(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeLivros.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
    }
    
 
    

    
}
