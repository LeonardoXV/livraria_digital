
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.telasgui.controllers.EditorasController;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.IOException;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



public class TelaDeCadastroDeEditoras extends Application implements Initializable,TelaGeral {
    
    
    @FXML private TextField txtCnpj,txtNome,txtRazao,txtEndereco,txtBairro,txtCep,txtTelefone,txtEstado,txtCida;
    @FXML private MaskTextField txtNumero,txtDDD;
    @FXML private Button buttonCadastrar,buttonAlterar,buttonDeletar;
    @FXML private Label labelEditoras;
    private final EditorasController controller=new EditorasController();
    private List<TextInputControl> texts=new ArrayList();
    private List<Editora>edioras;
    
    public static void main(String[] args) throws IOException {
        launch(args);
    }

 
    

    @Override
    public void start(Stage primaryStage) throws Exception {
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeCadastroDeEditoras.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelaDeCadastroDeEditoras.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Cadastro de Editoras");
       primaryStage.setFullScreen(true);
       primaryStage.show();
    }
        

    @Override
    public void initialize(URL location, ResourceBundle resources){
        this.buttonAlterar.setDisable(true);
        this.buttonDeletar.setDisable(true);
        this.txtNumero.setMask("NNNNNN");
        this.txtDDD.setMask("NN");
        
    
        
    }
    
     @FXML
    public void handlerCadastrarEditora(ActionEvent event)throws Exception {
        if(this.controller.validarCampos(this.preencheTexts())){
            this.controller.cadastrarEditora(this.txtCnpj.getText(),this.txtEndereco.getText(),this.txtTelefone.getText(),this.txtNome.getText(),Integer.parseInt(this.txtNumero.getText()),this.txtBairro.getText(),this.txtRazao.getText(),this.txtDDD.getText(),this.txtCida.getText(),this.txtCep.getText(),this.txtEstado.getText());
            JOptionPane.showMessageDialog(null, "EDITORA CADASTRADA COM SUCESSO");
            this.limpar();
        }
        }
    @FXML
    public void handlerDeletarEditora(ActionEvent event) throws IOException, Exception{
    
        try{
            this.controller.deletar(this.txtCnpj.getText());
            JOptionPane.showMessageDialog(null, "EDITORA DELETADA COM SUCESSO");
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
            controllerAdm.chamarEditora(event);
            
         }catch(Exception erro){
             JOptionPane.showMessageDialog(null, "IMPOSSÍVEL EXCLUIR EDITORA ESTÁ VINCULADA A UM LIVRO");
             
        }
        
    }
    
    @FXML
    public void handlerAtuaizarEditora(ActionEvent event) throws IOException, Exception{
         if(this.controller.validarCampos(this.preencheTexts())){
            this.edioras=this.controller.consultaAvancada("Editora",this.txtCnpj.getText(),"cnpj");
            this.controller.atualizarEditora(this.edioras.get(0),this.txtCnpj.getText(),this.txtEndereco.getText(),this.txtTelefone.getText(),this.txtNome.getText(),Integer.parseInt(this.txtNumero.getText()),this.txtBairro.getText(),this.txtRazao.getText(),this.txtDDD.getText(),this.txtCida.getText(),this.txtCep.getText(),this.txtEstado.getText());
            JOptionPane.showMessageDialog(null, "EDITORA ATUALIZADA");
        
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
            controllerAdm.chamarEditora(event);
         }
    }
    
   
    
     public List<TextInputControl> preencheTexts(){
        this.texts.add(this.txtBairro);
        this.texts.add(this.txtCep);
        this.texts.add(this.txtCida);
        this.texts.add(this.txtCnpj);
        this.texts.add(this.txtDDD);
        this.texts.add(this.txtEndereco);
        this.texts.add(this.txtEstado);
        this.texts.add(this.txtNome);
        this.texts.add(this.txtNumero);
        this.texts.add(this.txtRazao);
        this.texts.add(this.txtTelefone);
        return texts;
    }
     
     public void setConfiguracoesExternas(Editora editora,String titulo){
         this.buttonCadastrar.setDisable(true);
         this.txtCnpj.setText(editora.getCnpj());
         this.txtCnpj.setEditable(false);
         this.txtBairro.setText(editora.getBairro());
         this.txtCep.setText(editora.getCep());
         this.txtCida.setText(editora.getCidade());
         this.txtDDD.setText(editora.getDdd());
         this.txtEndereco.setText(editora.getEndereco());
         this.txtNome.setText(editora.getNomeFantasia());
         this.txtNumero.setText(Integer.toString(editora.getNumero()));
         this.txtRazao.setText(editora.getRazaoSocial());
         this.txtTelefone.setText(editora.getTelefone());
         this.buttonAlterar.setDisable(false);
         this.buttonDeletar.setDisable(false);
         this.txtEstado.setText(editora.getEstado());
         this.labelEditoras.setText(titulo);
         
      
     }
     
     public void limpar(){
         this.txtCnpj.setText("");
         this.txtBairro.setText("");
         this.txtCep.setText("");
         this.txtCida.setText("");
         this.txtDDD.setText("");
         this.txtEndereco.setText("");
         this.txtEstado.setText("");
         this.txtNome.setText("");
         this.txtNumero.setText("");
         this.txtRazao.setText("");
         this.txtTelefone.setText("");
         
     }
     
   
     
    
    
}
