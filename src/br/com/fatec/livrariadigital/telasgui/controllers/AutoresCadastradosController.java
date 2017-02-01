/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Editora;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author r2d2
 */
public class AutoresCadastradosController extends ControllerGeral {
    
    public void preencherListView(ListView lista){
        List<Autor> autores=new ArrayList();
        autores=this.consultaBasica("autor");
        ObservableList<String> options=FXCollections.observableArrayList();
        for (Autor autor : autores) {
            options.add(autor.getNome());
        }
        lista.setItems(options);
        
    }
    
}
