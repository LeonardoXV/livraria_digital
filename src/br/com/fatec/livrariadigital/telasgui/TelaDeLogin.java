package br.com.fatec.livrariadigital.telasgui;
import br.com.fatec.livrariadigital.aplicacao.OperacoesLogado;
import br.com.fatec.livrariadigital.aplicacao.OperacoesPedido;
import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.telasgui.controllers.ControllerGeral;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosController;
import br.com.fatec.livrariadigital.telasgui.controllers.LoginController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class TelaDeLogin extends Application implements TelaGeral {
    @FXML private TextField txtLogin,txtSenha;
    private List<TextInputControl>texts=new ArrayList();
    private LoginController controller=new LoginController();
    private final ControllerGeral controlador =new ControllerGeral();
    List<Usuario> listaUsers = new ArrayList();
    Usuario user = new Usuario();
    Usuario valido = new Usuario();
    OperacoesLogado logado=new OperacoesLogado();
    
    
    @FXML
    private void validarLogin(ActionEvent event) throws Exception{
        listaUsers = this.controlador.consultaBasica("usuario");
        valido = null;
        if(this.controller.validarCampos(this.preencheTexts())){
           if (listaUsers.isEmpty()){
               
           } else {
               for (Usuario usuario : listaUsers) {
                   if (usuario.getEmailUsuario().equals(txtLogin.getText()) &&
                          usuario.getSenha().equals(txtSenha.getText())) {
                          this.valido = usuario;
                   }
                   
               }
           }
           
           if(valido == null){
               JOptionPane.showMessageDialog(null, "Email e/ou senha inválido!");
           } else {
               JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
               Stage primaryStage = new Stage();
               Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeAdministrador.fxml"));
               Scene scene=new Scene(root);
               primaryStage.setScene(scene);
               primaryStage.setMaximized(true);
               this.logado.cadastraLogado(this.valido);
               this.valido = null;
               primaryStage.show();
           }
           
           
        }
        
    }
    
    private boolean usuarioSenha(Usuario user){
        
        return user.getSenha().equals(txtSenha.getText()) && user.getTipo().equals("INTERNO");
    }
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeLogin.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelaDeLogin.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Tela De Login");
       primaryStage.setMaximized(true);
       primaryStage.show();
       try{
           this.logado.removeLogadoAnterior();
       }catch(Exception erro){}
       
      
   
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    public void handlerLogar(ActionEvent event){
        
    }

    @Override
    public List<TextInputControl> preencheTexts() {
        this.texts.add(this.txtSenha);
        this.texts.add(this.txtLogin);
        return this.texts;
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
