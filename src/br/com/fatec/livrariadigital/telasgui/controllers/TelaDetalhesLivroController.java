/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.aplicacao.Imagem;
import br.com.fatec.livrariadigital.aplicacao.OperacoesLivro;
import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Carrinho;
import br.com.fatec.livrariadigital.entidades.Categoria;
import br.com.fatec.livrariadigital.entidades.Detalhes;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.telasgui.TelaBuscaDeLivros;
import br.com.fatec.livrariadigital.telasgui.TelaPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Victor
 */
public class TelaDetalhesLivroController implements Initializable {
    
    @FXML
    Label lbl_autor,lbl_editora,lbl_categoria,lbl_data,lbl_preco, txt_est;
    @FXML
    Text txt_titulo;
    @FXML
    TextField txt_quant;
    @FXML
    ImageView img_view;
    @FXML
    TextArea txt_sumario;
    @FXML
    Button btn_sumario;
    @FXML
    Button btn_add;
    Livro lv;
    Imagem img = new Imagem();
    Detalhes detalhe;
    Autor autores;
    Categoria categoria;
    Editora editora;
    private List<Livro>livros=new ArrayList();
    Carrinho carrinho_banco = new Carrinho();
    EntityManagerFactory en=Persistence.createEntityManagerFactory("persistence");
    OperacoesLivro op=new OperacoesLivro(this.en);
    
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
    private void fecharDetalhes(ActionEvent event){
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    @FXML
    private void chamarCarrinho(MouseEvent event) throws Exception{ 
       Stage stage2=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("../fxml/TelaGerenciadorDeCarrinho.fxml"));
       Parent root3 = (Parent)loader.load();
       Scene scene1=new Scene(root3);
       stage2.setScene(scene1);
       stage2.setMaximized(true);
       stage2.setResizable(false);
       ((Node)event.getSource()).getScene().getWindow().hide();  
       stage2.show();
    }
    
     @FXML
    private void handleBuscarCategoria(MouseEvent event) throws Exception{
       ((Node)event.getSource()).getScene().getWindow().hide();
       Stage stage=new Stage();
       FXMLLoader loader=new FXMLLoader(getClass().getResource("../fxml/TelaBuscaDeLivros.fxml"));
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
    private void add_carrinho(ActionEvent event){
        if (Integer.parseInt(txt_quant.getText()) >  lv.getQuantidade_livro()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ADICIONAR LIVROS");
            alerta.setContentText("QUANTIDADE INVÁLIDA!");
            alerta.show();
        } else {
            try {
                int cont = Integer.parseInt(txt_quant.getText());
                for (int i = 0; i < cont; i++) {
                livros.add(lv);
                carrinho_banco.setLivros(livros);
                op.cadastrarNoBancoCarrinho(carrinho_banco);
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("ADICIONAR LIVROS");
                alerta.setContentText("LIVRO ADICIONADO COM SUCESSO");
                txt_quant.setText("");
                alerta.show();
            }
            } catch (Exception e) {
                
            }
        }
    }
    
    @FXML
    private void voltarJanela(ActionEvent event) throws Exception{
       
    }
    
    public void setInfoDetalhes(Livro livro) throws IOException{
        this.lv = livro;
        detalhe = livro.getDetahes();
        autores = livro.getAutores().get(0);
        categoria = livro.getCategorias().get(0);
        editora = livro.getEditora();
        txt_titulo.setText(livro.getTitulo());
        txt_titulo.setWrappingWidth(200.0);
        txt_titulo.setTextAlignment(TextAlignment.LEFT);
        lbl_autor.setText(autores.getNome());
        lbl_editora.setText(editora.getNomeFantasia());
        lbl_categoria.setText(categoria.getNome());
        lbl_data.setText("DATA: " + detalhe.getDataDePublicacao().toLocaleString().substring(0,10));
        lbl_preco.setText("PREÇO: " + String.format("R$ %.2f", livro.getPrecoDeVenda()));
        txt_sumario.setText(detalhe.getResumo());
        img_view.setImage(img.converterFile(livro.getImagem()));
        txt_est.setText("ESTOQUE: "+Integer.toString(livro.getQuantidade_livro()) + " UNI.");
        if (livro.getQuantidade_livro()==0) {
            btn_add.setDisable(true);
            txt_quant.setDisable(true);
        }
        btn_sumario.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    Stage stage=new Stage();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("../fxml/TelaSumarioLivro.fxml"));
                    Parent root=null;
                    try {
                        root = (Parent)loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TelaDetalhesLivroController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    TelaSumarioLivroController det = loader.<TelaSumarioLivroController>getController();
                    det.setInform(livro);
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("DETALHES LIVRO");
                    stage.setAlwaysOnTop(true);
                    stage.toFront();
                    stage.setResizable(false);
       stage.show();
                }
            });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
