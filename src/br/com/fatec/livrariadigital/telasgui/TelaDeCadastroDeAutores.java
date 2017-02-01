
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.telasgui.controllers.AutoresController;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class TelaDeCadastroDeAutores extends Application implements Initializable,TelaGeral{
    
    @FXML MaskTextField txtDataNasce,txtDataFale;
    @FXML TextField txtNome,txtLocalNasc,txtLocalMorte,txtBiografia;
    @FXML Button buttonCadastrar,buttonAlterar,buttonDeletar;
    @FXML Label labelAutores;
    List<TextInputControl>  texts=new ArrayList();
    List<Autor>autores=new ArrayList();
     private final AutoresController controller=new AutoresController();
     public static void main(String[] args) {
         launch(args);
         
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeCadastroDeAutores.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelaDeCadastroDeAutores.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Cadastro de Autores");
       primaryStage.setFullScreen(true);
       primaryStage.show();    
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.txtLocalMorte.setDisable(true);
        this.txtDataFale.setMask("NN/NN/NNNN");
       this.buttonAlterar.setDisable(true);
        this.buttonDeletar.setDisable(true);
        this.txtDataNasce.setMask("NN/NN/NNNN");
    }
    
    @FXML
    public void handlerCadastrarAutores(ActionEvent event)throws Exception {
       if(this.controller.validarCampos(this.preencheTexts())){
           if(this.txtDataNasce.getText().length()==10){
               if(this.txtDataFale.getText().isEmpty()){
                   try{
                        this.controller.cadastrarAutor(this.txtNome.getText(),this.txtDataNasce.getText(),this.txtDataFale.getText(),this.txtLocalNasc.getText(),this.txtLocalMorte.getText(),this.txtBiografia.getText());
                    }catch(IllegalArgumentException ex){
                        this.controller.cadastrarAutor(this.txtNome.getText(),this.txtDataNasce.getText(),this.txtLocalNasc.getText(),this.txtBiografia.getText());
                    }
                    JOptionPane.showMessageDialog(null, "AUTOR CADASTRADO COM SUCESSO");
                    this.limpar();
               }else if(this.txtDataFale.getText().length()==10){
                     try{
                        this.controller.cadastrarAutor(this.txtNome.getText(),this.txtDataNasce.getText(),this.txtDataFale.getText(),this.txtLocalNasc.getText(),this.txtLocalMorte.getText(),this.txtBiografia.getText());
                    }catch(IllegalArgumentException ex){
                        this.controller.cadastrarAutor(this.txtNome.getText(),this.txtDataNasce.getText(),this.txtLocalNasc.getText(),this.txtBiografia.getText());
                    }
                    JOptionPane.showMessageDialog(null, "AUTOR CADASTRADO COM SUCESSO");
                    this.limpar();
               }else{
                   this.txtDataFale.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
                   JOptionPane.showMessageDialog(null, "PREENCHA A DATA DE FALECIMENTO CORRETAMENTE: NN/NN/NNNN");
               }
           }
           else{
                this.txtDataNasce.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
                JOptionPane.showMessageDialog(null, "PREENCHA A DATA DE NASCIMENTO CORRETAMENTE: NN/NN/NNNN");
           }
       }
}
    @FXML
    public void handlerAtualizarAutores(ActionEvent event)throws Exception {
        if(this.controller.validarCampos(this.preencheTexts())){
            this.autores=this.controller.consultaAvancada("Autor",txtNome.getText(),"nome");
            this.controller.atualizarAutor(autores.get(0),txtNome.getText(),txtDataNasce.getText(),txtDataFale.getText(),txtLocalNasc.getText(),txtLocalMorte.getText(),txtBiografia.getText());
            JOptionPane.showMessageDialog(null, "AUTOR ATUALIZADO COM SUCESSO");
            List<Autor>autor=this.controller.consultaAvancada("Autor",this.txtNome.getText(),"nome");
            ((Node)(event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeAdministrador.fxml"));
            Stage stage2=new Stage();
            Parent root3 = (Parent)loader.load();
            TelaDeAdministrador controllerAdm=loader.<TelaDeAdministrador>getController();
            Scene scene1=new Scene(root3);
            stage2.setScene(scene1);
            stage2.setMaximized(true);
            stage2.setResizable(false);
            stage2.show();
            controllerAdm.anchorPane.getChildren().clear();
            controllerAdm.chamarAutor(event);
        }
 
    }
    @FXML
    public void handlerDeletarAutores(ActionEvent event)throws Exception {
        try{
        this.controller.deletar(this.txtNome.getText());
        JOptionPane.showMessageDialog(null, "AUTOR DELETADO COM SUCESSO");
        
        ((Node)(event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeAdministrador.fxml"));
        Stage stage2=new Stage();
        Parent root3 = (Parent)loader.load();
        TelaDeAdministrador controllerAdm=loader.<TelaDeAdministrador>getController();
        Scene scene1=new Scene(root3);
        stage2.setScene(scene1);
        stage2.setMaximized(true);
        stage2.setResizable(false);
        stage2.show();
      
        controllerAdm.anchorPane.getChildren().clear();
        controllerAdm.chamarAutor(event);
        }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "IMPOSSÍVEL EXCLUIR AUTOR ESTÁ VINCULADO A UM LIVRO");
        }
  
        
    }
   
    @FXML 
    public void handleKeyEvent(javafx.scene.input.MouseEvent ke){
    
      if(this.txtDataFale.getText().length()==10){
           this.txtLocalMorte.setDisable(false);
      }
      else{
          this.txtLocalMorte.setDisable(true);
      }
     
      
    }
     public void setConfiguracoesExternas(Autor autor,String titulo){
         this.txtBiografia.setText(autor.getBiografia());
         this.txtDataNasce.setText(autor.getDataNasce().toLocaleString().substring(0,10));       
         this.txtLocalNasc.setText(autor.getLocalNasc());
         this.txtNome.setText(autor.getNome());
         this.txtNome.setEditable(false);
         this.labelAutores.setText(titulo);
         this.buttonAlterar.setDisable(false);
         this.buttonDeletar.setDisable(false);
         this.buttonCadastrar.setDisable(true);
         try{
         this.txtDataFale.setText(autor.getDataFale().toLocaleString().substring(0,10));
         this.txtLocalMorte.setText(autor.getLocalMorte());
         }catch(NullPointerException  erro){
             this.txtDataFale.setText("");
             this.txtLocalMorte.setText("");
         }
     }
       public List<TextInputControl> preencheTexts(){
        this.texts.add(txtNome);
        this.texts.add(txtBiografia);
        this.texts.add(txtLocalNasc);
        this.texts.add(txtDataNasce);
      
        return texts;
    }

    @Override
    public void limpar() {
        this.txtDataFale.setText("");
        for (TextInputControl text : texts) {
            text.setText("");
        }
    }
}