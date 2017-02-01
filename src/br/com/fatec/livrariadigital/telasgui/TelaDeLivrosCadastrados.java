/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.telasgui.controllers.EditorasCadastradasController;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosCadastradosController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javax.swing.JOptionPane;


public class TelaDeLivrosCadastrados extends Application implements Initializable {
   @FXML private ListView<String> lista;
   @FXML private TextField txtIsbn;
   private  List<Livro>livro=new ArrayList();
    private final LivrosCadastradosController controller=new LivrosCadastradosController();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeLivrosCadastrados.fxml"));
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
    
    @FXML
    public void handleChamarLivro(ActionEvent event) throws IOException {
        if(this.txtIsbn.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "SELECIONE UM LIVRO PARA REALIZAR A PESQUISA");
        }
        else
        {
            try{
                this.livro=this.controller.consultaAvancada("Livro",this.txtIsbn.getText(),"isbn");
            }catch(IndexOutOfBoundsException erro){
                JOptionPane.showMessageDialog(null, "LIVRO NÃO CADASTRADO");
            }
            
        }
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       this.controller.preencherListView(this.lista);
    }
    
     @FXML
    public void handlerClickListViewItem(MouseEvent event) throws IOException{
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 1){
                this.livro=this.controller.consultaAvancada("Livro",lista.getSelectionModel().getSelectedItem(),"titulo");
                this.txtIsbn.setText(livro.get(0).getIsbn());
            }
        }
        
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
    
 
    
    public String getIsbn(){
        return this.txtIsbn.getText();
    }
    
}