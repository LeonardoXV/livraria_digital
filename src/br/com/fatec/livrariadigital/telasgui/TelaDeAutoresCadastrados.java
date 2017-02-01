package br.com.fatec.livrariadigital.telasgui;
import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.telasgui.controllers.AutoresCadastradosController;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;


public class TelaDeAutoresCadastrados extends Application implements Initializable{
    
    @FXML private ListView<String> lista;
    @FXML private TextField txtNome;
    private List<Autor>autores;
    
    @FXML private final AutoresCadastradosController controller=new AutoresCadastradosController();
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeAutoresCadastrados.fxml"));
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
    public void handleChamarAutor(ActionEvent event) throws IOException {
            if(this.txtNome.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "SELECIONE UM AUTOR PARA REALIZAR A PESQUISA");
        }
        else
        {
            try{
                this.autores=this.controller.consultaAvancada("Autor",this.txtNome.getText(),"nome");
                this.invocar();
                
               
            }catch(IndexOutOfBoundsException erro){
                JOptionPane.showMessageDialog(null, "AUTOR NÃO ENCONTRADO");
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
                List<Autor>autor=this.controller.consultaAvancada("Autor",lista.getSelectionModel().getSelectedItem(),"nome");
                this.txtNome.setText(autor.get(0).getNome());
            }
        }
        
    }
    
    @FXML
    public void invocar() throws IOException{
        List<Autor>autor=this.controller.consultaAvancada("Autor",this.txtNome.getText(),"nome");
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeAutores.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        TelaDeCadastroDeAutores controller = fxmlLoader.<TelaDeCadastroDeAutores>getController();
        controller.setConfiguracoesExternas(autor.get(0),"EDIÇÃO DE AUTORES");
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
    }
  
     @FXML
    public void handlerNovoAutor(ActionEvent event) throws IOException{
        ((Node)(event.getSource())).getScene().getWindow().hide();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/TelaDeCadastroDeAutores.fxml"));     
        Parent root = (Parent)fxmlLoader.load();          
        Scene scene = new Scene(root); 
        stage.setScene(scene);    
        stage.show();
    }
    
    public String getNome(){
        return this.txtNome.getText();
    }
}
