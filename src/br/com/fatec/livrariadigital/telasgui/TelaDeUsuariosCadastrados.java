/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosCadastradosController;
import br.com.fatec.livrariadigital.telasgui.controllers.UsuariosCadastradosController;
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
 * @author r2d2
 */
public class TelaDeUsuariosCadastrados extends Application implements Initializable {
 @FXML private ListView<String> lista;
   @FXML private TextField txtEmail;
   private  List<Usuario>usuarios=new ArrayList();
    private final UsuariosCadastradosController  controller=new UsuariosCadastradosController ();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeUsuariosCadastrados.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelasCadastrados.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Tela De Usuarios");
       primaryStage.setFullScreen(true);
       primaryStage.show();
       
    }
      
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    public void handleChamarUsuario(ActionEvent event) throws IOException {
        if(this.txtEmail.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "SELECIONE UM USUARIO PARA REALIZAR A PESQUISA");
        }
        else
        {
            try{
                this.usuarios=this.controller.consultaAvancada("Usuario",this.txtEmail.getText(),"emailUsuario");
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
            try{
            if(event.getClickCount() == 1){
                this.usuarios=this.controller.consultaAvancada("Usuario",lista.getSelectionModel().getSelectedItem(),"emailUsuario");
                this.txtEmail.setText(usuarios.get(0).getEmailUsuario());
            }
            }catch(Exception erro){
                
            }
        }
        
    }

    public String getIEmail(){
        return this.txtEmail.getText();
    }
    
}
