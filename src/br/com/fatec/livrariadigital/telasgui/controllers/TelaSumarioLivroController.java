/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Livro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author Victor
 */
public class TelaSumarioLivroController implements Initializable {
    
    @FXML
    private Label nomeLivro,isbnLivro,formatoLivro,paginaLivro,dataPublica
            ,nomeAutor,dataAutor,localAutor,livroEditora;
    @FXML
    private TextArea txt_sumario;
    
    
    @FXML
    public void setInform(Livro livro){
        String sumario = livro.getDetahes().getIndice().replace(".", "\n");
        txt_sumario.setText(sumario);
        nomeLivro.setText("TÍTULO: " + livro.getTitulo());
        isbnLivro.setText("ISBN: " + livro.getIsbn());
        formatoLivro.setText("FORMATO: " + livro.getDetahes().getFormato());
        paginaLivro.setText("NÚMERO DE PÁGINAS: " + livro.getDetahes().getNumPaginas());
        dataPublica.setText("DATA PUBLICAÇÃO: " + livro.getDetahes().getDataDePublicacao().toLocaleString().substring(0,10));
        nomeAutor.setText("NOME AUTOR: " + livro.getAutores().get(0).getNome());
        dataAutor.setText("DATA NASCIMENTO: " + livro.getAutores().get(0).getDataNasce());
        localAutor.setText("LOCAL NASCIMENTO: " + livro.getAutores().get(0).getLocalNasc());
        livroEditora.setText("EDITORA: " + livro.getEditora().getNomeFantasia());
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
