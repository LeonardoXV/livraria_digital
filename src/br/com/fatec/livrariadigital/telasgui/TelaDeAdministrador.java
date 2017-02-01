/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.telasgui.controllers.AutoresController;
import br.com.fatec.livrariadigital.telasgui.controllers.EditorasController;
import br.com.fatec.livrariadigital.telasgui.controllers.UsuarioController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.jboss.jandex.Main;

/**
 *
 * @author leovieira
 */
public class TelaDeAdministrador extends Application implements Initializable {
    
    private EditorasController controller=new EditorasController();
    private AutoresController controllerAutores=new AutoresController();
    private String cnpj;


    @FXML
    AnchorPane anchorPane;
    @FXML
    public void handleUsuarios(ActionEvent event) throws Exception{
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       AnchorPane pg = new AnchorPane();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeUsuario.fxml"));
       Parent root3 = (Parent)loader.load();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
    }
    
    @FXML
    public void handlerChamarUsuario(ActionEvent event) throws IOException{
       AnchorPane pg = new AnchorPane();
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeUsuariosCadastrados.fxml"));
       Parent root3 = (Parent)loader.load();
       TelaDeUsuariosCadastrados controllerUsu=loader.<TelaDeUsuariosCadastrados>getController();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       Button pesquisar=new Button();
       pesquisar.setText("PESQUISAR");
       pesquisar.setLayoutX(578);
       pesquisar.setLayoutY(175);
       pesquisar.setStyle("-fx-background-color:#27ae60;-fx-pref-width :180;-fx-pref-height:40;-fx-text-fill:white;-fx-font-size:20;-fx-font-family: Open Sans Extrabold;");
       pesquisar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String email= controllerUsu.getIEmail();
                if(email.isEmpty()){JOptionPane.showMessageDialog(null, "SELECIONE UMA EDITORA PARA REALIZAR A PESQUISA");}
                else{
                List<Usuario>usuarios=controller.consultaAvancada("Usuario",email ,"emailUsuario");
                try {
                    anchorPane.getChildren().clear();
                    Stage stage2=new Stage();
                    AnchorPane pg = new AnchorPane();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeUsuario.fxml"));
                    Parent root3 = (Parent)loader.load();
                    TelaDeCadastroDeUsuario telaUsuario=loader.<TelaDeCadastroDeUsuario>getController();
                    telaUsuario.setConfiguracoesExternas(usuarios.get(0),"EDIÇÃO DE USUARIOS");
                    pg.getChildren().add(root3);
                    anchorPane.getChildren().add(pg);
                    //stage2.initStyle(StageStyle.UNDECORATED);
                    AnchorPane.setBottomAnchor(pg, .0);
                    AnchorPane.setLeftAnchor(pg, 0.0);
                    AnchorPane.setRightAnchor(pg, 0.0);
                    AnchorPane.setTopAnchor(pg, 0.0);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        });
       anchorPane.getChildren().add(pesquisar);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 10.0);
       //stage2.show();
    }
    
    
    @FXML
    private void chamarCadastroLivros(ActionEvent event) throws Exception{
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       AnchorPane pg = new AnchorPane();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeLivros.fxml"));
       Parent root3 = (Parent)loader.load();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
    }
    

    
    @FXML
    public void chamarEditora(ActionEvent event) throws Exception{
       AnchorPane pg = new AnchorPane();
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeEditorasCadastradas.fxml"));
       Parent root3 = (Parent)loader.load();
       TelaDeEditorasCadastradas controllerEdi=loader.<TelaDeEditorasCadastradas>getController();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       Button pesquisar=new Button();
       pesquisar.setText("PESQUISAR");
       pesquisar.setLayoutX(578);
       pesquisar.setLayoutY(175);
       pesquisar.setStyle("-fx-background-color:#27ae60;-fx-pref-width :180;-fx-pref-height:40;-fx-text-fill:white;-fx-font-size:20;-fx-font-family: Open Sans Extrabold;");
       pesquisar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String cnpj= controllerEdi.getCnpj();
                if(cnpj.isEmpty()){JOptionPane.showMessageDialog(null, "SELECIONE UMA EDITORA PARA REALIZAR A PESQUISA");}
                else{
                List<Editora>editoras=controller.consultaAvancada("Editora",cnpj ,"cnpj");
                try {
                    anchorPane.getChildren().clear();
                    Stage stage2=new Stage();
                    AnchorPane pg = new AnchorPane();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeEditoras.fxml"));
                    Parent root3 = (Parent)loader.load();
                    TelaDeCadastroDeEditoras telaEditora=loader.<TelaDeCadastroDeEditoras>getController();
                    telaEditora.setConfiguracoesExternas(editoras.get(0),"EDIÇÃO DE EDITORAS");
                    pg.getChildren().add(root3);
                    anchorPane.getChildren().add(pg);
                    //stage2.initStyle(StageStyle.UNDECORATED);
                    AnchorPane.setBottomAnchor(pg, .0);
                    AnchorPane.setLeftAnchor(pg, 0.0);
                    AnchorPane.setRightAnchor(pg, 0.0);
                    AnchorPane.setTopAnchor(pg, 0.0);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        });
       anchorPane.getChildren().add(pesquisar);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 10.0);
       //stage2.show();
    }
    
    @FXML
    private void chamarCadastroEditora(ActionEvent event) throws Exception{
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       AnchorPane pg = new AnchorPane();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeEditoras.fxml"));
       Parent root3 = (Parent)loader.load();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
    }
    
@FXML
   public void chamarAutor(ActionEvent event) throws Exception{
       AnchorPane pg = new AnchorPane();
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeAutoresCadastrados.fxml"));
       Parent root3 = (Parent)loader.load();
       TelaDeAutoresCadastrados controllerEdi=loader.<TelaDeAutoresCadastrados>getController();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       Button pesquisar=new Button();
       pesquisar.setText("PESQUISAR");
       pesquisar.setLayoutX(578);
       pesquisar.setLayoutY(175);
       pesquisar.setStyle("-fx-background-color:#27ae60;-fx-pref-width :180;-fx-pref-height:40;-fx-text-fill:white;-fx-font-size:20;-fx-font-family: Open Sans Extrabold;");
       pesquisar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String nome= controllerEdi.getNome();
                if(nome.isEmpty()){JOptionPane.showMessageDialog(null, "SELECIONE UM AUTOR PARA REALIZAR A PESQUISA");}
                else{
                List<Autor>autores=controller.consultaAvancada("Autor",nome ,"nome");
                try {
                    anchorPane.getChildren().clear();
                    Stage stage2=new Stage();
                    AnchorPane pg = new AnchorPane();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeAutores.fxml"));
                    Parent root3 = (Parent)loader.load();
                    TelaDeCadastroDeAutores telaAutores=loader.<TelaDeCadastroDeAutores>getController();
                    telaAutores.setConfiguracoesExternas(autores.get(0),"EDIÇÃO DE AUTORES");
                    pg.getChildren().add(root3);
                    anchorPane.getChildren().add(pg);
                    //stage2.initStyle(StageStyle.UNDECORATED);
                    AnchorPane.setBottomAnchor(pg, .0);
                    AnchorPane.setLeftAnchor(pg, 0.0);
                    AnchorPane.setRightAnchor(pg, 0.0);
                    AnchorPane.setTopAnchor(pg, 0.0);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
                
            }
        });
       anchorPane.getChildren().add(pesquisar);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 10.0);
       //stage2.show();
    }
    
    @FXML
    private void chamarCadastroAutor(ActionEvent event) throws Exception{
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       AnchorPane pg = new AnchorPane();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeAutores.fxml"));
       Parent root3 = (Parent)loader.load();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
    }
@FXML  
public void chamarLivro(ActionEvent event) throws Exception{
       AnchorPane pg = new AnchorPane();
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeLivrosCadastrados.fxml"));
       Parent root3 = (Parent)loader.load();
       TelaDeLivrosCadastrados controllerLivr=loader.<TelaDeLivrosCadastrados>getController();
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       Button pesquisar=new Button();
       pesquisar.setText("PESQUISAR");
       pesquisar.setLayoutX(578);
       pesquisar.setLayoutY(175);
       pesquisar.setStyle("-fx-background-color:#27ae60;-fx-pref-width :180;-fx-pref-height:40;-fx-text-fill:white;-fx-font-size:20;-fx-font-family: Open Sans Extrabold;");
       pesquisar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                String isbn= controllerLivr.getIsbn();
                if(isbn.isEmpty()){JOptionPane.showMessageDialog(null, "SELECIONE UM AUTOR PARA REALIZAR A PESQUISA");}
                else{
                   try{
                       List<Livro>livros=controller.consultaAvancada("Livro",isbn ,"isbn");
                       livros.get(0);
                try {
                    anchorPane.getChildren().clear();
                    Stage stage2=new Stage();
                    AnchorPane pg = new AnchorPane();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeLivros.fxml"));
                    Parent root3 = (Parent)loader.load();
                    TelaDeCadastroDeLivros telaLivros=loader.<TelaDeCadastroDeLivros>getController();
                    telaLivros.setConfiguracoesExternas(livros.get(0),"EDIÇÃO DE LIVROS");
                    pg.getChildren().add(root3);
                    anchorPane.getChildren().add(pg);
                    //stage2.initStyle(StageStyle.UNDECORATED);
                    AnchorPane.setBottomAnchor(pg, .0);
                    AnchorPane.setLeftAnchor(pg, 0.0);
                    AnchorPane.setRightAnchor(pg, 0.0);
                    AnchorPane.setTopAnchor(pg, 0.0);
                } catch (IOException ex) {
                    Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                }
                   } catch (Exception EX){
                        JOptionPane.showMessageDialog(null, "LIVRO NÃO ENCONTRADO!");
                   } 
                  
               
                }
                
            }
        });
       anchorPane.getChildren().add(pesquisar);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 10.0);
       //stage2.show();
    }
    

    
    @FXML
    private void handleSair(ActionEvent event) throws IOException{
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaPrincipal.fxml"));
       Parent root3 = (Parent)loader.load();
       Scene scene1=new Scene(root3);
       stage2.setScene(scene1);
       stage2.setMaximized(true);
       stage2.setResizable(false);
       ((Node)event.getSource()).getScene().getWindow().hide();
       stage2.show();
       
    }
    
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Button inicio=new Button();
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeAdministrador.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelaDeAdministrador.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Tela de Administrador");
       primaryStage.setMaximized(true);
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.show();
    }

 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      AnchorPane pg = new AnchorPane();
       //anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/PainelAdministrativo.fxml"));
       Parent root3 = null;
        try {
            root3 = (Parent)loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
       //stage2.show();*/
    }
    
    @FXML
    public void handlerInicio(ActionEvent event)throws Exception {
         AnchorPane pg = new AnchorPane();
       anchorPane.getChildren().clear();
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/PainelAdministrativo.fxml"));
       Parent root3 = null;
        try {
            root3 = (Parent)loader.load();
        } catch (IOException ex) {
            Logger.getLogger(TelaDeAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
       pg.getChildren().add(root3);
       anchorPane.getChildren().add(pg);
       //stage2.initStyle(StageStyle.UNDECORATED);
       AnchorPane.setBottomAnchor(pg, .0);
       AnchorPane.setLeftAnchor(pg, 0.0);
       AnchorPane.setRightAnchor(pg, 0.0);
       AnchorPane.setTopAnchor(pg, 0.0);
    
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
    
}
