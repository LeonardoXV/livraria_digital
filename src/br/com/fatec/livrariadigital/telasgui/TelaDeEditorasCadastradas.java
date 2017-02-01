
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.telasgui.controllers.EditorasCadastradasController;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javax.swing.JOptionPane;


public class TelaDeEditorasCadastradas extends Application implements Initializable {

    @FXML private ListView<String> lista;
    @FXML private TextField txtCnpj;
    private TelaDeAdministrador administrador=new TelaDeAdministrador();
    private List<Editora>editoras;
    private EditorasCadastradasController controller=new EditorasCadastradasController();
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeEditorasCadastradas.fxml"));
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
    public void handleChamarEditora(ActionEvent event) throws IOException {
        if(this.txtCnpj.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "SELECIONE UMA EDITORA PARA REALIZAR A PESQUISA");
        }
        else
        {
            try{
                this.editoras=this.controller.consultaAvancada("Editora",this.txtCnpj.getText(),"cnpj");
                this.invocar();
            }catch(IndexOutOfBoundsException erro){
                JOptionPane.showMessageDialog(null, "EDITORA NÃO CADASTRADA");
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
                List<Editora>editora=this.controller.consultaAvancada("Editora",lista.getSelectionModel().getSelectedItem(),"nomeFantasia");
                this.txtCnpj.setText(editora.get(0).getCnpj());
            }
        }
        
    }
    @FXML
    public void handlerNovaEditora(ActionEvent event)throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeEditoras.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
    }
    
  
    
    public void invocar() throws IOException{
        List<Editora> editora=this.controller.consultaAvancada("Editora", this.txtCnpj.getText(),"cnpj");
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeEditoras.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        TelaDeCadastroDeEditoras controller = fxmlLoader.<TelaDeCadastroDeEditoras>getController();
        controller.setConfiguracoesExternas(editora.get(0),"EDIÇÃO DE EDITORAS");
        
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
       
    }
    
    public String getCnpj(){
        return this.txtCnpj.getText();
    }
  
}
