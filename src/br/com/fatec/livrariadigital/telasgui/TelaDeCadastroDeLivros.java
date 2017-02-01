package br.com.fatec.livrariadigital.telasgui;
import br.com.fatec.livrariadigital.aplicacao.Imagem;
import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Categoria;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosController;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

public class TelaDeCadastroDeLivros extends Application implements Initializable,TelaGeral {
    
    @FXML private TextField txtIsbn, txtTitulo;
    @FXML MaskTextField txtPrecoDeVenda, txtNumPaginas,txtPrecoCusto,txtQuantidade;
    @FXML private MaskTextField  txtDataDePublicacao;
    @FXML private TextArea txtResumo, txtIndice,txtAutores,txtCategorias;
    @FXML  private ComboBox boxCategorias, boxAutores,boxFormato,boxEditoras;
    @FXML private ImageView imagem;
    @FXML private Button buttonCadastrar,buttonAlterar,buttonDeletar;
    @FXML private Label labelLivros;
    private final Imagem img=new Imagem();
    private File file;
    private final LivrosController controller=new LivrosController();
    private List<String>nomes=new ArrayList();
    private List<Autor>autores=new ArrayList();
    private List<Categoria>categorias=new ArrayList();
    private List<Editora> editora;
    private Editora editora1;
    private List<Livro>livro;
    private Set<String>listCatego= new HashSet<String>();
    private Set<String>listAutores= new HashSet<String>();
    private int NumCategorias=0;
    private int NumAutores=0;
    private List<TextInputControl>texts=new ArrayList();
    
    
    
   
    @Override
    public void start(Stage primaryStage) throws IOException  {
        
       Pane root= FXMLLoader.load(getClass().getResource("fxml/TelaDeCadastroDeLivros.fxml"));
       Scene scene=new Scene(root);
       primaryStage.setScene(scene);
       String css=this.getClass().getResource("css/TelaDeLogin.css").toExternalForm();
       scene.getStylesheets().add(css);
       primaryStage.setTitle("Cadastro de Livros");
       primaryStage.setFullScreen(true);
       primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
          public void handle(WindowEvent we) {
              System.out.println("COLQUE AQUI O CODIGO");
          }
      });        
       primaryStage.show();
       
    }
      
    public static void main(String[] args) {
        
        launch(args);
        
    }
   
    
    @FXML
    public void handlerCadastrarLivro(ActionEvent event)throws Exception{
        if(this.controller.validarCampos(this.preencheTexts()))
        {
        try{
            this.boxEditoras.getValue();
            this.boxFormato.getValue();
        if(this.txtDataDePublicacao.getText().length()==10){
        
            this.editora=this.controller.consultaAvancada("Editora",this.boxEditoras.getValue().toString(),"nomeFantasia");
            this.preencheTexts();
            if(this.file!=null)
            {
                this.controller.CadastrarLivro(this.txtResumo.getText(),this.txtIndice.getText(),this.boxFormato.getValue().toString(),Integer.parseInt(this.txtNumPaginas.getText()),this.txtDataDePublicacao.getText(),Double.parseDouble(this.txtPrecoDeVenda.getText()),this.txtIsbn.getText(),this.txtTitulo.getText(),this.categorias,this.editora.get(0),this.autores,this.file,Integer.parseInt(this.txtQuantidade.getText()),Double.parseDouble(this.txtPrecoCusto.getText()));
                this.limpar();
                this.boxAutores.setValue("Autores");
                this.boxCategorias.setValue("Categorias");
                this.boxEditoras.setValue("Editoras");
                this.boxFormato.setValue("Formato");
                JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO COM SUCESSO");
                this.imagem.setImage(null);
            }else
            {
                JOptionPane.showMessageDialog(null, "SELECIONE UMA IMAGEM PARA O LIVRO");
            }
        }
    else
    {
        this.txtDataDePublicacao.setStyle("-fx-border-color:red;-fx-border-style: segments(6.5);");
        JOptionPane.showMessageDialog(null, "PREENCHA A DATA CORRETAMENTE: NN/NN/NNNN");
    }
        }catch(NullPointerException erro){
            JOptionPane.showMessageDialog(null, "CERTIFIQUE-SE QUE A EDITORA E FORMATO FORAM SELECIONADOS");
        }  
        
        this.limparCategorias(event);
        this.limparAutores(event);
    }
        
    }
    @FXML
    public void handlerSelecionarImagem(ActionEvent event){
        
        try {
            this.file=this.controller.selecionarImagem(this.imagem);
            System.out.println(this.file.getAbsoluteFile());
        } catch (IOException ex) {
            System.out.println("Erro: ");
        }
    }
    
    @FXML
    public void handlerAdicionarEditora(ActionEvent event){
         this.editora=this.controller.consultaAvancada("Editora",this.boxEditoras.getValue().toString(),"nomeFantasia");
    }

    @FXML
    public void  handlerAdicionarAutores(ActionEvent event){
         try{         
         this.listAutores.add(this.boxAutores.getValue().toString());
         if(listAutores.size()>this.NumAutores)
         {
             try{
            List<Autor>lista=this.controller.consultaAvancada("Autor",this.boxAutores.getValue().toString(),"nome");
            Autor autor=lista.get(0);
            this.autores.add(autor);
            this.NumAutores=this.listAutores.size();
            this.txtAutores.setText(this.txtAutores.getText()+this.boxAutores.getValue().toString()+"\n");}catch(Exception erro){
                
            }
         }
       } catch (NullPointerException ex){
           
       }
    }
    
   
    
    @FXML
    public void  handlerAdicionarCategoria(ActionEvent event){
         try{
         this.listCatego.add(this.boxCategorias.getValue().toString());
         if(listCatego.size()>this.NumCategorias)
         {
            
            List<Categoria>lista=this.controller.consultaAvancada("Categoria",this.boxCategorias.getValue().toString(),"nome");
            Categoria categoria=lista.get(0);
            this.categorias.add(categoria);
            this.NumCategorias=this.listCatego.size();
            this.txtCategorias.setText(this.txtCategorias.getText()+this.boxCategorias.getValue().toString()+"\n");
          
         }}catch(Exception erro){
                
            }
    }
    

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
       this.controller.preencerComboBox("editora", this.boxEditoras);
       this.controller.preencerComboBox("autor", this.boxAutores);
       this.controller.preencerComboBox("categoria",this.boxCategorias); 
       this.buttonAlterar.setDisable(true);
       this.buttonDeletar.setDisable(true);
       List<String>nomes=new ArrayList();
       nomes.add("BROCHURA");
       nomes.add("CAPA DURA");
       ObservableList<String> options=FXCollections.observableArrayList(nomes);
       this.boxFormato.setItems(options);
       this.txtCategorias.setEditable(false);
       this.txtAutores.setEditable(false);
       this.txtDataDePublicacao.setMask("NN/NN/NNNN");
       this.txtNumPaginas.setMask("NNNNNNNN");
       this.txtPrecoCusto.setMask("N!.N!");
       this.txtPrecoDeVenda.setMask("N!.N!");
       this.txtQuantidade.setMask("NNNNNNNN");
       
    }
    
    public List<TextInputControl> preencheTexts(){
        this.texts.add(txtIsbn);
        this.texts.add(this.txtAutores);
        this.texts.add(this.txtCategorias);
        this.texts.add(this.txtDataDePublicacao);
        this.texts.add(this.txtIndice);
        this.texts.add(this.txtNumPaginas);
        this.texts.add(this.txtPrecoDeVenda);
        this.texts.add(this.txtResumo);
        this.texts.add(this.txtTitulo);
        this.texts.add(this.txtQuantidade);
        this.texts.add(this.txtPrecoCusto);
        return texts;
    }
    @FXML
    public void handlerDeletar(ActionEvent event) throws IOException, Exception{
        try{
            this.controller.deletar(this.txtIsbn.getText());
            JOptionPane.showMessageDialog(null, "LIVRO DELETADO COM SUCESSO");
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
            controllerAdm.chamarLivro(event);
        }catch(MySQLIntegrityConstraintViolationException erro){
            JOptionPane.showMessageDialog(null, "Impossivel Excluir,Livro Associado a um autor");
        }
    }
    

    
    @FXML
    public void handlerAtualizarLivro(ActionEvent event) throws IOException, Exception{
        if(this.controller.validarCampos(this.preencheTexts())==true){
        this.editora=this.controller.consultaAvancada("Editora",this.boxEditoras.getValue().toString(),"nomeFantasia");
        this.livro=this.controller.consultaAvancada("Livro",this.txtIsbn.getText(),"isbn");
        this.controller.atualizarLivro(livro.get(0),this.txtResumo.getText(),this.txtIndice.getText(),this.boxFormato.getValue().toString(),Integer.parseInt(this.txtNumPaginas.getText()),this.txtDataDePublicacao.getText(),Double.parseDouble(this.txtPrecoDeVenda.getText()),this.txtIsbn.getText(),this.txtTitulo.getText(), categorias, editora.get(0), autores, file,Double.parseDouble(this.txtPrecoCusto.getText()),Integer.parseInt(this.txtQuantidade.getText()));
        JOptionPane.showMessageDialog(null, "ATUALIZAÇÃO EFETUADA COM SUCESSO");
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
        controllerAdm.chamarLivro(event);
        }
        
       
       
    }
    
     public void setConfiguracoesExternas(Livro livro,String titulo) throws IOException{
         this.editora=new ArrayList();
         this.boxEditoras.getSelectionModel().select(livro.getEditora().getNomeFantasia());
         this.file=livro.getImagem();
         this.autores=livro.getAutores();
         this.categorias=livro.getCategorias();
         this.txtIsbn.setText(livro.getIsbn());
         this.txtIsbn.setEditable(false);
         this.labelLivros.setText(titulo);
         this.buttonAlterar.setDisable(false);
         this.buttonDeletar.setDisable(false);
         this.buttonCadastrar.setDisable(true);
         this.imagem.setImage(this.controller.getImagem().converterFile(livro.getImagem()));
         this.txtPrecoCusto.setText(Double.toString(livro.getPrecoDeCusto()));
         this.txtQuantidade.setText(Integer.toString(livro.getQuantidade_livro()));
         List<Categoria>categorias=livro.getCategorias();
         for (Categoria categoria : categorias) {
             this.txtCategorias.setText(this.txtCategorias.getText()+categoria.getNome()+"\n");
             this.listCatego.add(categoria.getNome());
             this.NumCategorias++;
         }
         List<Autor>autores=livro.getAutores();
         for (Autor autor : autores) {
             this.txtAutores.setText(this.txtAutores.getText()+autor.getNome()+"\n");
             this.listAutores.add(autor.getNome());
             this.NumAutores++;
         }
         this.txtDataDePublicacao.setText(livro.getDetahes().getDataDePublicacao().toLocaleString().substring(0,10));
         this.txtIndice.setText(livro.getDetahes().getIndice());
         this.txtNumPaginas.setText(Integer.toString(livro.getDetahes().getNumPaginas()));
         this.txtPrecoDeVenda.setText(Double.toString(livro.getPrecoDeVenda()));
         this.txtResumo.setText(livro.getDetahes().getResumo());
         this.txtTitulo.setText(livro.getTitulo());
         this.boxFormato.getSelectionModel().select(livro.getDetahes().getFormato());
         
     }

    @Override
    public void limpar() {
       for (TextInputControl text : texts) {
            text.setText("");
        }
    }
    
    @FXML 
    public void limparAutores(ActionEvent event){
        this.autores=new ArrayList();
        this.txtAutores.setText("");
        this.listAutores=new HashSet();
        this.NumAutores=0;
        this.boxAutores.getSelectionModel().clearSelection();
      
       
    }
    @FXML
    public void limparCategorias(ActionEvent event){
        this.categorias=new ArrayList();
        this.txtCategorias.setText("");
        this.listCatego=new HashSet();
        this.NumCategorias=0;
        this.boxCategorias.getSelectionModel().clearSelection();

    }

 
}