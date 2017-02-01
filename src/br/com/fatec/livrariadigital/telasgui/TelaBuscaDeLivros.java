/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.aplicacao.CarrinhoDeCompras;
import br.com.fatec.livrariadigital.aplicacao.Imagem;
import br.com.fatec.livrariadigital.aplicacao.OperacoesAutor;
import br.com.fatec.livrariadigital.aplicacao.OperacoesEditora;
import br.com.fatec.livrariadigital.aplicacao.OperacoesLivro;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Carrinho;
import br.com.fatec.livrariadigital.entidades.Categoria;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.telasgui.controllers.ControllerGeral;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosController;
import br.com.fatec.livrariadigital.telasgui.controllers.TelaDetalhesLivroController;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Victor
 */
public class TelaBuscaDeLivros extends Application implements Initializable{
    
    
    private final TelaPrincipal tp = new TelaPrincipal();
    EntityManagerFactory en=Persistence.createEntityManagerFactory("persistence");
    OperacoesAutor operacoesAutor=new OperacoesAutor(this.en);
    OperacoesEditora operacoesEditora=new OperacoesEditora(this.en);
    private final LivrosController ControladorLivro=new LivrosController();
    Carrinho carrinho_banco = new Carrinho();
    private final ControllerGeral Controller = new ControllerGeral();
    @FXML
    ComboBox cb_pesquisa;
    double pagin;
    double res;
    CarrinhoDeCompras car = new CarrinhoDeCompras();
    DecimalFormat df = new DecimalFormat("0.00");
    private Imagem img=new Imagem();
    @FXML
    AnchorPane anchorpage = new AnchorPane();
    @FXML
    private final Pagination pagination = new Pagination();
    int i = 0; // Contador do createPage
    List<Livro> lista = new ArrayList();
    List<Livro> carrinho = new ArrayList();
    Livro livro_carrinho = new Livro();
    Livro livro_detalhes = new Livro();    
    List<Livro> novoLivro = new ArrayList();
    List<Autor> listaAutor = new ArrayList();
    List<Editora> listaEditora = new ArrayList();
    private List<Livro>livros=new ArrayList();
    @FXML
    private Label lbl_resultado;
    @FXML
    private TextField txt_pesquisa;
    
    public void setInfo(String texto, int consulta){
        //Seta os valores da label e do textBox
        lbl_resultado.setText(texto.toUpperCase());
        txt_pesquisa.setText(texto);
        carregarLivros(consulta); //Carrega os livros na lista
    }
    
    @FXML
    private void handleLogin(MouseEvent e) throws Exception{      
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDeLogin.fxml"));
       Parent root3 = (Parent)loader.load();
       Scene scene1=new Scene(root3);
       stage2.setScene(scene1);
       stage2.setMaximized(true);
       stage2.setResizable(false);
       ((Node)e.getSource()).getScene().getWindow().hide();
       stage2.show();
    }
    
    @FXML
     private void handleBuscarCategoria(MouseEvent event) throws Exception{  
       Object source = event.getSource();
       Label clickedLabel = (Label) source; 
       String categoriaLower = clickedLabel.getText().toLowerCase();
       String categoriaUpper = Character.toUpperCase(categoriaLower.charAt(0)) + categoriaLower.substring(1); 
       novoLivro = new ArrayList();
       this.lista=this.ControladorLivro.consultaBasica("livro");
                for (Livro livro : lista) {
                    List<Categoria> categorias = livro.getCategorias();
                    for (Categoria categoria : categorias) {
                        if(categoria.getNome().equals(categoriaUpper)){
                            novoLivro = categoria.getLivros();
                        }
                    }
                }
                this.lista = new ArrayList();
                if (novoLivro.isEmpty()) {
                } else {
                    this.lista = novoLivro;
                }
                
                pagin = lista.size();
                if (pagin == 0 || pagin > 0 && pagin < 6) {
                     res = 1;
                 }else if(pagin >= 6){
                     res = Math.ceil(pagin/6.0);
                 }
                 pagination.setPageCount((int) res);
        
            lbl_resultado.setText(" CATEGORIA  >  " + clickedLabel.getText().toUpperCase());
            pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
            //anchorpage.getChildren().add(pagination);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 25.0);
            AnchorPane.setRightAnchor(pagination, .0);
            AnchorPane.setTopAnchor(pagination, 25.0);
            pagination.setPageFactory(new Callback<Integer,Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    return createPage(pageIndex);
                }
            });
     }
    
    @FXML
    private void handleTelaPrincipal(MouseEvent event) throws Exception{
       ((Node)event.getSource()).getScene().getWindow().hide();
       Stage stage=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaPrincipal.fxml"));
       Parent root = (Parent)loader.load();
       Scene scene=new Scene(root);
       stage.setScene(scene);
       stage.setMaximized(true);
       stage.setResizable(false);
       stage.show();
    }
    
    public void carregarLivros(int consulta){
       String categoriaLower = txt_pesquisa.getText().toLowerCase();
       String categoriaUpper = Character.toUpperCase(categoriaLower.charAt(0)) + categoriaLower.substring(1);
        switch (consulta) {
            case 1: //Pesquisar pelos autores do Livro
                this.lista = new ArrayList();
                this.listaAutor = this.operacoesAutor.consultaComLikeAutor(txt_pesquisa.getText());
                if(!listaAutor.isEmpty()){
                   this.lista = listaAutor.get(0).getLivros(); 
                }
                break;
            case 2: //Pesquisar titulo do Livro
                this.lista = new ArrayList();
                this.lista=this.operacoesAutor.consultaComLike(txt_pesquisa.getText());
                break;
            case 3: //Pesquisar pela categoria do Livro
                this.lista = new ArrayList();
                this.lista=this.ControladorLivro.consultaBasica("livro");
                novoLivro = new ArrayList();
                for (Livro livro : lista) {
                    List<Categoria> categorias = livro.getCategorias();
                    for (Categoria categoria : categorias) {
                        if(categoria.getNome().equals(categoriaUpper)){
                            novoLivro = categoria.getLivros();
                        }
                    }
                }
                this.lista = new ArrayList();
                if (novoLivro.isEmpty()) {
                    
                } else {
                    this.lista = novoLivro;
                }
                break;
            case 4:
                this.lista = new ArrayList();
                this.listaEditora = this.operacoesEditora.consultaLikeEditora(txt_pesquisa.getText());
                if(!listaEditora.isEmpty()){
                    this.lista = listaEditora.get(0).getLivros();
                }
            default:
                break;
        }
       //Setta o numero de paginas que o "Pagination" terá
       pagin = lista.size();
       if (pagin == 0 || pagin > 0 && pagin < 6) {
            res = 1;
        }else if(pagin >= 6){
            res = Math.ceil(pagin/6.0);
        }
        pagination.setPageCount((int) res);
        
    }
   
    
    @FXML
    private void handleCarrinho(MouseEvent e) throws Exception{
       ((Node)e.getSource()).getScene().getWindow().hide();
       this.operacoesAutor.cadastrarNoBanco(carrinho_banco);
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaGerenciadorDeCarrinho.fxml"));
       Parent root3 = (Parent)loader.load();
       TelaGerenciadorDeCarrinho add = loader.<TelaGerenciadorDeCarrinho>getController();
       Scene scene1=new Scene(root3);
       stage2.setScene(scene1);
       stage2.setMaximized(true);
       stage2.setResizable(false);
       stage2.show();
    }
    
    @FXML
    private void handleBuscaLivro(ActionEvent event) throws Exception{
        //Carrega os livros de acordo com o paremetro da ComboBox Pesquisa
        if (cb_pesquisa.getValue() == null || txt_pesquisa.getText().equals("")) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("BUSCA DE LIVROS");
            alerta.setContentText("SELECIONE UM MÉTODO DE PESQUISA");
            alerta.show();
        } else if(cb_pesquisa.getValue().equals("AUTOR")){
            carregarLivros(1);
            lbl_resultado.setText("AUTOR > " +txt_pesquisa.getText().toUpperCase());
            pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
            //anchorpage.getChildren().add(pagination);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 25.0);
            AnchorPane.setRightAnchor(pagination, .0);
            AnchorPane.setTopAnchor(pagination, 25.0);
            pagination.setPageFactory(new Callback<Integer,Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    return createPage(pageIndex);
                }
            });  
        } else if(cb_pesquisa.getValue().equals("TÍTULO")){
            carregarLivros(2);
            lbl_resultado.setText("TÍTULO > "+txt_pesquisa.getText().toUpperCase());
             pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
            //anchorpage.getChildren().add(pagination);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 25.0);
            AnchorPane.setRightAnchor(pagination, .0);
            AnchorPane.setTopAnchor(pagination, 25.0);
            pagination.setPageFactory(new Callback<Integer,Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    return createPage(pageIndex);
                }
            }); 
        } else if(cb_pesquisa.getValue().equals("CATEGORIA")){
            carregarLivros(3);
            lbl_resultado.setText(" CATEGORIA  >  " + txt_pesquisa.getText().toUpperCase());
            pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
            //anchorpage.getChildren().add(pagination);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 25.0);
            AnchorPane.setRightAnchor(pagination, .0);
            AnchorPane.setTopAnchor(pagination, 25.0);
            pagination.setPageFactory(new Callback<Integer,Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    return createPage(pageIndex);
                }
            }); 
            
        } else if (cb_pesquisa.getValue().equals("EDITORA")){
            carregarLivros(4);
            lbl_resultado.setText(" EDITORA  >  " + txt_pesquisa.getText().toUpperCase());
            pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
            //anchorpage.getChildren().add(pagination);
            AnchorPane.setBottomAnchor(pagination, 10.0);
            AnchorPane.setLeftAnchor(pagination, 25.0);
            AnchorPane.setRightAnchor(pagination, .0);
            AnchorPane.setTopAnchor(pagination, 25.0);
            pagination.setPageFactory(new Callback<Integer,Node>() {
                @Override
                public Node call(Integer pageIndex) {
                    return createPage(pageIndex);
                }
            }); 
        } 
    }
    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/TelaBuscaDeLivros.fxml"));
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public int itemsPerPage() {
        return 6;
    }
    
    
    public void ChangeText(Label text, String fontOpen, double size){
       text.setAlignment(Pos.CENTER);
       text.setFont(Font.font(fontOpen, size));
    }
    
    public HBox createPage(int pageIndex) { //Criação dos Componentes dentro das páginas
        //contador = list.size();
        HBox box = new HBox(52.0);
        int page = pageIndex * itemsPerPage();
        int pagina = page + itemsPerPage();
        if(lista.isEmpty()){
            String FontOpen = "Open Sans Extrabold";
                VBox vbx = new VBox(5);
                vbx.setAlignment(Pos.CENTER);
                HBox element = new HBox();
                String Style_color = "-fx-text-fill: #BDC3C7";
                Label lbl_naoEncontrado = new Label("TERMO NÃO ENCONTRADO :/");
                Label lbl_tenteNovamente = new Label("FAVOR TENTE NOVAMENTE.");
                lbl_naoEncontrado.setFont(Font.font(FontOpen, 70));
                lbl_naoEncontrado.setTranslateX(170.0);
                lbl_naoEncontrado.setStyle(Style_color);
                lbl_tenteNovamente.setFont(Font.font(FontOpen, 40));
                lbl_tenteNovamente.setTranslateX(350.0);
                lbl_tenteNovamente.setStyle(Style_color);
                vbx.getChildren().addAll(lbl_naoEncontrado, lbl_tenteNovamente);
                element.getChildren().addAll(vbx);
                box.getChildren().add(element);
                pagina = lista.size();
                pagination.setMinSize(500.0, 510.0);
                return box;
        } else if (pagina >= lista.size() ){
            pagina = lista.size();
        }
        
        for (i = page; i < pagina; i++) {           
            HBox element = new HBox();
            ImageView imgin = new ImageView();
            imgin.setFitWidth(170);
            imgin.setFitHeight(250);
            
            HBox hBox_outter = new HBox();
            String style_outter = "-fx-border-color: black;"
              + "-fx-border-width: 3;";
            hBox_outter.setStyle(style_outter);
            hBox_outter.setAlignment(Pos.CENTER);
            
            VBox vbx = new VBox(5);
            vbx.setAlignment(Pos.CENTER);
            
            Separator sep = new Separator();
            sep.setStyle("-fx-max-height: 40;" + "-fx-max-width: 50; "
            + "-fx-background-color: black; -fx-background-radius: 2;");
            
            HBox hBox_insidder = new HBox();
            hBox_insidder.setStyle(style_outter);   
            
            String FontOpen = "Open Sans Extrabold";
            
            
            Label lbl_categoria = new Label();
            lbl_categoria.setText(lista.get(i).getCategorias().get(0).getNome());
            Text texto = new Text();
            texto.setY(10.0);
            texto.setFill(Paint.valueOf("#33495F"));
            texto.setFont(Font.font(FontOpen, 15));
            texto.setText(lista.get(i).getTitulo());
            
            if(texto.getText().length() > 15 && texto.getText().length() < 24){
                texto.setWrappingWidth(170);
                texto.setTextAlignment(TextAlignment.CENTER);
                texto.setText(lista.get(i).getTitulo().substring(0,lista.get(i).getTitulo().length()) + "...");
            } else if (texto.getText().length() <= 15){
                texto.setText(lista.get(i).getTitulo() + "\n");
            } else if (texto.getText().length() >= 24){
                texto.setWrappingWidth(170);
                texto.setTextAlignment(TextAlignment.CENTER);
                texto.setText(lista.get(i).getTitulo().substring(0,24) + "...");
            }
            
            String valor = df.format(lista.get(i).getPrecoDeVenda());
            Label lbl_autor = new Label(lista.get(i).getAutores().get(0).getNome());
            lbl_autor.setMaxWidth(170);
            Label lbl_preco = new Label("R$: " + valor);
            try {
                imgin.setImage(img.converterFile(lista.get(i).getImagem()));
            } catch (IOException ex){
                System.out.println("ERRO");
            }
            

            ChangeText(lbl_categoria, FontOpen, 14.0);
            ChangeText(lbl_autor, FontOpen, 16.0);
            ChangeText(lbl_preco, FontOpen, 20.0);
            lbl_preco.setTextFill(Paint.valueOf("#E67E22"));

            
            Button btn_detalhes = new Button("DETALHES");
            btn_detalhes.setFont(Font.font(FontOpen, 12));
            btn_detalhes.setId(Integer.toString(i));
            btn_detalhes.setMaxSize(100, 30);
            
            btn_detalhes.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    
                    Stage stage2=new Stage();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaDetalhesLivro.fxml"));
                    Parent root3 = null;
                    try {
                        root3 = (Parent)loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TelaDetalhesLivroController det = loader.<TelaDetalhesLivroController>getController();
                    Object source = event.getSource();
                    Button botao = (Button) source; 
                    livro_detalhes = lista.get(Integer.parseInt(botao.getId()));
                    if(livro_detalhes != null){
                        try {
                            det.setInfoDetalhes(livro_detalhes);
                        } catch (Exception exc) {
                            System.out.println("Erro ao carregar detalhes do Livro!");
                        }
                    } 

                    Scene scene1=new Scene(root3);
                    stage2.setScene(scene1);
                    stage2.setMaximized(true);
                    stage2.setResizable(false);
                    stage2.show();
                             }
            });
            
            
            
            Button btn_adicionar = new Button("ADICIONAR");
            btn_adicionar.setId(Integer.toString(i));
            btn_adicionar.setFont(Font.font(FontOpen, 12));
            btn_adicionar.setMaxSize(100, 30);
            
            if (lista.get(i).getQuantidade_livro()==0) {
                btn_adicionar.setDisable(true);
                btn_adicionar.setText("INDISPONÍVEL");
                btn_adicionar.setMinSize(120.0, 30.0);
            }
            
             btn_adicionar.setOnAction(new EventHandler<ActionEvent>() { //Evento do Botao adicionar ao carrinho
             @Override public void handle(ActionEvent event) {    
                //FadeTransition ft = new FadeTransition(Duration.millis(3000), );
                //ft.setFromValue(0.0);
                //ft.setToValue(1.0);
                //ft.play();
                Object source = event.getSource();
                Button clickedBtn = (Button) source; 
                //carrinho.add(lista.get(Integer.parseInt(clickedBtn.getId()))); //adiciona um livro a lista carrinho
                livro_carrinho = lista.get(Integer.parseInt(clickedBtn.getId()));
                livros.add(livro_carrinho);
                carrinho_banco.setLivros(livros);
                car.adicionarLivro(livro_carrinho);
                Stage novostage=new Stage();//POPUP
                FXMLLoader loader2=new FXMLLoader(getClass().getResource("fxml/AdicionarAoCarrinho.fxml"));
                Parent root5 = null;
                 try {
                     root5 = (Parent)loader2.load();
                 } catch (IOException ex) {
                     Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                 }
  
                Scene scene=new Scene(root5);
                novostage.setScene(scene);
                novostage.setAlwaysOnTop(true);
                novostage.setY(300.00);
                novostage.setX(500.00);
                novostage.initStyle(StageStyle.UNDECORATED);
                novostage.setResizable(false);
 
                novostage.show();
                }
            });  
            
            //Label txt_page = new Label("Page" + (pageIndex+1));
            hBox_insidder.getChildren().add(imgin);
            vbx.getChildren().add(hBox_insidder);
            vbx.getChildren().addAll(lbl_categoria,texto,lbl_autor,sep,
                    lbl_preco,btn_detalhes, btn_adicionar); //Adiciona os Componentes Dentro de um VBOX
            element.getChildren().addAll(vbx);
            box.getChildren().add(element);     //Adiciona o VBOX Dentro do BOX
     
        }
        return box;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagination.setStyle("-fx-page-information-visible: false; -fx-arrows-visible: true;");
        anchorpage.getChildren().add(pagination);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 25.0);
        AnchorPane.setRightAnchor(pagination, .0);
        AnchorPane.setTopAnchor(pagination, 25.0);
        pagination.setPageFactory(new Callback<Integer,Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
        "CATEGORIA",
        "AUTOR",
        "TÍTULO",
        "EDITORA"        
        );
        
        cb_pesquisa.setItems(options);
        
    }
    
}
