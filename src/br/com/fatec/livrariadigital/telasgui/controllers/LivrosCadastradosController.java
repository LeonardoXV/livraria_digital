/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Livro;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class LivrosCadastradosController extends ControllerGeral{
       public void preencherListView(ListView lista){
        List<Livro>livros=new ArrayList();
        livros=this.consultaBasica("livro");
        ObservableList<String> options=FXCollections.observableArrayList();
        for (Livro livro : livros) {
            options.add(livro.getTitulo());
        }
        lista.setItems(options);
        
    }
    
}
