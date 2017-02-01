/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui;

import br.com.fatec.livrariadigital.aplicacao.CarrinhoDeCompras;
import br.com.fatec.livrariadigital.aplicacao.Imagem;
import br.com.fatec.livrariadigital.aplicacao.OperacoesLivro;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Carrinho;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.telasgui.controllers.LivrosController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Victor
 */
public class TelaGerenciadorDeCarrinho extends Application implements Initializable{
    
    @FXML
    private ListView<Livro> lista = new ListView();      
    private List<Livro> cl = new ArrayList();
    List<Carrinho> carrinho = new ArrayList();
    @FXML
    private Label lbl_total;
    double valorTotal = 0;
    int id_btn=0;
    List<Carrinho> carrinhos_banco=new ArrayList();
    List<Livro> livroCarrinho = new ArrayList();
    private final LivrosController ControladorLivro=new LivrosController();
    EntityManagerFactory en=Persistence.createEntityManagerFactory("persistence");
    OperacoesLivro op=new OperacoesLivro(this.en);
    /*public void setCarrinhoLivros(){
       //this.livroCarrinho = carrinho.getLivros();
       this.carrinhos_banco = this.ControladorLivro.consultarCarrinho();
       this.livroCarrinho = this.carrinhos_banco.get(0).getLivros();
       
       ObservableList<Livro> items = FXCollections.observableArrayList(livroCarrinho);

        for (Livro livr : livroCarrinho) { 
            valorTotal += livr.getPrecoDeVenda(); //Definir o valor total dos Livros;
        }
        
        lista.setCellFactory(new Callback<ListView<Livro>, ListCell<Livro>>() {

            @Override
            public ListCell<Livro> call(ListView<Livro> param) {
                ListCell<Livro> cell = new ListCell<Livro>() {
                    
                    @Override
                    protected void updateItem(Livro livro, boolean vazio) {
                        super.updateItem(livro, vazio);
                        if (livro != null) {
                            Imagem img = new Imagem();
                            ImageView imgin = new ImageView();
                            List<Autor> autor= new ArrayList();
                            autor = livro.getAutores();
                            VBox vBox = new VBox(new Text(livro.getTitulo()), 
                                    new Text(autor.get(0).getNome()),
                                    new Text(String.format("R$ %.2f", livro.getPrecoDeVenda())));
                            vBox.setMaxWidth(350.0);
                            vBox.setMinWidth(350.0);
                            HBox hBox = new HBox();
                            HBox img_box = new HBox();
                            imgin.setFitHeight(100.0);
                            imgin.setFitWidth(80.0);
                            try {
                                imgin.setImage(img.converterFile(livro.getImagem()));
                            } catch (IOException ex) {
                                Logger.getLogger(TelaGerenciadorDeCarrinho.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            img_box.getChildren().add(imgin);
                            HBox hboxexit= new HBox();
                            
                            hboxexit.setAlignment(Pos.CENTER_RIGHT);
                            hboxexit.setMaxWidth(300.0);
                            hboxexit.setMinWidth(300.0);
                            hboxexit.setTranslateX(300.0);
                            //hboxexit.getChildren().add(btn_excluir);
                            hBox.getChildren().addAll(img_box,vBox,hboxexit);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                            lbl_total.setText(String.format("R$ %.2f", valorTotal));
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
                
            }
        });
       lista.setItems(items);
    }*/
    
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
        
    @FXML
    private void handleBuscarCategoria(MouseEvent event) throws Exception{
       ((Node)event.getSource()).getScene().getWindow().hide();
       Stage stage=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("fxml/TelaBuscaDeLivros.fxml"));
       Parent root = (Parent)loader.load();
       Object source = event.getSource();
       Label clickedLabel = (Label) source; 
       TelaBuscaDeLivros TelaBusca = loader.<TelaBuscaDeLivros>getController();
       
       String categoriaLower = clickedLabel.getText().toLowerCase();
       String categoriaUpper = Character.toUpperCase(categoriaLower.charAt(0)) + categoriaLower.substring(1);
       TelaBusca.setInfo(categoriaUpper, 3); //Set Valor
       
       Scene scene=new Scene(root);
       stage.setScene(scene);
       stage.setMaximized(true);
       stage.setResizable(false);
       stage.show();
        
    }
    
    @FXML
    private void excluirItem(ActionEvent event) throws Exception{
       ObservableList<Livro> items = lista.getItems();
       this.valorTotal -= lista.getItems().get(lista.getSelectionModel().getSelectedIndex()).getPrecoDeVenda();
       if (!items.isEmpty()){
          items.remove(lista.getSelectionModel().getSelectedIndex());
       }

        if (items.isEmpty()) {
            this.valorTotal = 00.00;
            lbl_total.setText(Double.toString(this.valorTotal));
        }
        
        lista.setCellFactory(new Callback<ListView<Livro>, ListCell<Livro>>() {

            @Override
            public ListCell<Livro> call(ListView<Livro> param) {
                ListCell<Livro> cell = new ListCell<Livro>() {
                    
                    @Override
                    protected void updateItem(Livro livro, boolean vazio) {
                        super.updateItem(livro, vazio);
                        if (livro != null) {
                            Imagem img = new Imagem();
                            ImageView imgin = new ImageView();
                            List<Autor> autor= new ArrayList();
                            autor = livro.getAutores();
                            VBox vBox = new VBox(new Text(livro.getTitulo()), 
                                    new Text(autor.get(0).getNome()),
                                    new Text(String.format("R$ %.2f", livro.getPrecoDeVenda())));
                            vBox.setMaxWidth(350.0);
                            vBox.setMinWidth(350.0);
                            HBox hBox = new HBox();
                            HBox img_box = new HBox();
                            imgin.setFitHeight(100.0);
                            imgin.setFitWidth(80.0);
                            try {
                                imgin.setImage(img.converterFile(livro.getImagem()));
                            } catch (IOException ex) {
                                Logger.getLogger(TelaGerenciadorDeCarrinho.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            img_box.getChildren().add(imgin);
                            HBox hboxexit= new HBox();
                            
                            hboxexit.setAlignment(Pos.CENTER_RIGHT);
                            hboxexit.setMaxWidth(300.0);
                            hboxexit.setMinWidth(300.0);
                            hboxexit.setTranslateX(300.0);
                            //hboxexit.getChildren().add(btn_excluir);
                            hBox.getChildren().addAll(img_box,vBox,hboxexit);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                            lbl_total.setText(String.format("R$ %.2f", valorTotal));
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
                
            }
        });
       lista.setItems(items);
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    @FXML
    private void excluirTudo(ActionEvent event) throws Exception{
            lista.getItems().removeAll(cl);
            
            try{
            this.carrinhos_banco=this.ControladorLivro.consultaBasica("carrinho");
            //carrinho=this.ControladorLivro.consultaBasica("Carrinho");
                for (Carrinho carrinho : carrinhos_banco) {
                    this.op.deletarCarrinho(carrinho);
                }
            }catch(Exception erro){
                System.out.println("Erro ao deletar o carrinho!");
            }
            this.valorTotal = 0.0;
            lbl_total.setText(Double.toString(this.valorTotal));
            carrinhos_banco = new ArrayList();
            lista.refresh();
            
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
    
    @Override
    public void start(Stage stage) throws Exception {  
        Parent root = FXMLLoader.load(getClass().getResource("fxml/TelaGerenciadorDeCarrinho.fxml"));
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

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    this.carrinhos_banco = this.ControladorLivro.consultaBasica("carrinho");
    
        if (!carrinhos_banco.isEmpty()) {
            //this.livroCarrinho = this.carrinhos_banco.get(0).getLivros();
            for (Carrinho carrinho : carrinhos_banco) {
                livroCarrinho = carrinho.getLivros();
                for (Livro livro : livroCarrinho) {
                    cl.add(livro);
                }
        }
        
        ObservableList<Livro> items = FXCollections.observableArrayList(cl);
        //valorTotal = 0;
        for (Livro livr : cl) { 
            this.valorTotal += livr.getPrecoDeVenda(); //Definir o valor total dos Livros;  
        }
            System.out.println(this.valorTotal);
        lbl_total.setText(String.format("R$ %.2f", this.valorTotal));
        
        lista.setCellFactory(new Callback<ListView<Livro>, ListCell<Livro>>() {

            @Override
            public ListCell<Livro> call(ListView<Livro> param) {
                ListCell<Livro> cell = new ListCell<Livro>() {
                    
                    @Override
                    protected void updateItem(Livro livro, boolean vazio) {
                        super.updateItem(livro, vazio);
                        if (livro != null) {
                            Imagem img = new Imagem();
                            ImageView imgin = new ImageView();
                            List<Autor> autor= new ArrayList();
                            autor = livro.getAutores();
                            VBox vBox = new VBox(new Text(livro.getTitulo()), 
                                    new Text(autor.get(0).getNome()),
                                    new Text(String.format("R$ %.2f", livro.getPrecoDeVenda())));
                            vBox.setMaxWidth(350.0);
                            vBox.setMinWidth(350.0);
                            HBox hBox = new HBox();
                            HBox img_box = new HBox();
                            imgin.setFitHeight(100.0);
                            imgin.setFitWidth(80.0);
                            try {
                                imgin.setImage(img.converterFile(livro.getImagem()));
                            } catch (IOException ex) {
                                Logger.getLogger(TelaGerenciadorDeCarrinho.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            img_box.getChildren().add(imgin);
                            HBox hboxexit= new HBox();
                            
                            hboxexit.setAlignment(Pos.CENTER_RIGHT);
                            hboxexit.setMaxWidth(300.0);
                            hboxexit.setMinWidth(300.0);
                            hboxexit.setTranslateX(300.0);
                            //hboxexit.getChildren().add(btn_excluir);
                            hBox.getChildren().addAll(img_box,vBox,hboxexit);
                            hBox.setSpacing(10);
                            setGraphic(hBox);
                            
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
                
            }
        });
       lista.setItems(items);
       carrinhos_banco = new ArrayList();
    
    }
        
    
    }
    
    
}
