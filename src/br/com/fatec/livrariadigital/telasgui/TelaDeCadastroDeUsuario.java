
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.telasgui.controllers.UsuarioController;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputControl;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.Parent;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;



public class TelaDeCadastroDeUsuario extends Application implements Initializable,TelaGeral{
    
  
    
    private UsuarioController controller=new UsuarioController();
    @FXML TextField txtCpf,txtNome,txtSobreNome,txtEndereco,txtBairro,txtCidade,txtEstado,txtCep,txtSenha,txtRepita;
    @FXML MaskTextField txtNumero,txtNascimento,txtEmail;
    @FXML ComboBox boxSexo;
    @FXML Button buttonCadastrar,buttonAlterar,buttonDeletar;
    private String tipo="INTERNO";
    private List<TextInputControl>texts=new ArrayList();
    private  List<Usuario>usuarios=new ArrayList();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeCadastroDeUsuario.fxml"));
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
        ObservableList<String> options=FXCollections.observableArrayList("M","F");
        this.boxSexo.setItems(options);
        this.buttonAlterar.setDisable(true);
        this.buttonDeletar.setDisable(true);
        this.txtNascimento.setMask("NN/NN/NNNN");
        this.txtNumero.setMask("NNNNN");
        this.txtEmail.setMask("M!@M!.P!");
      
    }
    
    @FXML
    public void handlerCadastrar(ActionEvent event){
       
        if(this.controller.validarCampos(this.preencheTexts())){
                if(this.txtSenha.getText().equals(this.txtRepita.getText())){
                    if(this.txtNascimento.getText().length() == 10){
                            try{
                            char sexo=this.boxSexo.getValue().toString().charAt(0);
                            this.controller.cadastrar(this.txtEmail.getText(),this.txtNome.getText(),this.txtSobreNome.getText(),this.txtSenha.getText(), new Date(this.txtNascimento.getText()),sexo,this.txtCpf.getText(),this.txtEndereco.getText(),Integer.parseInt(this.txtNumero.getText()),this.txtBairro.getText(),this.txtCidade.getText() ,this.txtEstado.getText(),this.txtCep.getText(),tipo);
                            JOptionPane.showMessageDialog(null, "USUARIO CADASTRADO");
                            this.limpar();
                        }catch(NullPointerException erro){
                            JOptionPane.showMessageDialog(null, "SELECIONE O SEXO DO USUÁRIO");
                        } 
                    } else {
                        JOptionPane.showMessageDialog(null, "PREENCHA A DATA MM/DD/AAAA");
                        this.txtNascimento.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
                    }
                    
                    
                }
                else
                {
                     JOptionPane.showMessageDialog(null, "AS SENHAS NÃO CONFEREM");
                }
            }
        
        
        
    }
    
    @FXML
    public void handlerDeletar(ActionEvent event) throws Exception{
        this.controller.deletar(this.txtEmail.getText());
        JOptionPane.showMessageDialog(null, "USUARIO DELETADO COM SUCESSO");
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
        controllerAdm.handlerChamarUsuario(event);
    }
    
    @FXML
    public void handlerAtualizar(ActionEvent event)throws Exception{
        
       if(this.controller.validarCamposAtualizar(this.preencheTexts())){
       if(this.txtSenha.getText().equals("")){
            JOptionPane.showMessageDialog(null, "DIGITE UMA SENHA");
       }
       else{
           if(this.txtSenha.getText().equals(this.txtRepita.getText())){
                if(this.txtNascimento.getText().length() == 10){
                   this.usuarios=this.controller.consultaAvancada("Usuario",this.txtEmail.getText(),"emailUsuario");
                
                Date data=new Date(this.txtNascimento.getText());
                
                this.controller.atualizar(usuarios.get(0),this.txtEmail.getText(),this.txtNome.getText(), this.txtSobreNome.getText(),this.txtSenha.getText(), data, this.boxSexo.getValue().toString().charAt(0),this.txtCpf.getText(),this.txtEndereco.getText(),Integer.parseInt(this.txtNumero.getText()),this.txtBairro.getText(),this.txtCidade.getText(),this.txtEstado.getText(),this.txtCep.getText());
        
                JOptionPane.showMessageDialog(null, "USUARIO ATUALIZADO");
         
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
                controllerAdm.handlerChamarUsuario(event); 
                } else {
                    JOptionPane.showMessageDialog(null, "PREENCHA A DATA MM/DD/AAAA");
                    this.txtNascimento.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
                }
        }
           else{
               JOptionPane.showMessageDialog(null, "AS SENHA NÃO CONFEREM");
           }
       }
       }
    }


    @Override
    public List<TextInputControl> preencheTexts() {
       this.texts.add(this.txtEmail);
       this.texts.add(this.txtBairro);
       this.texts.add(this.txtCep);
       this.texts.add(this.txtCidade);
       this.texts.add(this.txtCpf);
       this.texts.add(this.txtEndereco);
       this.texts.add(this.txtEstado);
       this.texts.add(this.txtNascimento);
       this.texts.add(this.txtNome);
       this.texts.add(this.txtNumero);
       this.texts.add(this.txtRepita);
       this.texts.add(this.txtSenha);
       this.texts.add(this.txtSobreNome);
       return this.texts;
    }

    @Override
    public void limpar() {
        for (TextInputControl text : texts) {
            text.setText("");
        }
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setConfiguracoesExternas(Usuario usuario,String titulo){
        this.txtEmail.setText(usuario.getEmailUsuario());
        this.txtEmail.setEditable(false);
        this.buttonAlterar.setDisable(false);
        this.buttonDeletar.setDisable(false);
        this.buttonCadastrar.setDisable(true);
        this.txtBairro.setText(usuario.getBairro());
        this.txtCep.setText(usuario.getCep());
        this.txtCidade.setText(usuario.getCidade());
        this.txtCpf.setText(usuario.getCpf());
        this.txtEndereco.setText(usuario.getEndereco());
        this.txtEstado.setText(usuario.getEstado());
        this.txtNascimento.setText(usuario.getDataNasc().toLocaleString().substring(0,10));
        this.txtNome.setText(usuario.getNome());
        this.txtNumero.setText(Integer.toString(usuario.getNumero()));
        this.txtSobreNome.setText(usuario.getSobreNome());

        char sexo=usuario.getSexo();
        this.boxSexo.setValue(sexo);
    }
}
