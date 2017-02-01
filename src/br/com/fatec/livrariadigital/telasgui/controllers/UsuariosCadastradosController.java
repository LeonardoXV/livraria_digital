/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author r2d2
 */
public class UsuariosCadastradosController extends ControllerGeral {
      public void preencherListView(ListView lista){
        List<Usuario>usuarios=new ArrayList();
        usuarios=this.consultaBasica("Usuario");
        ObservableList<String> options=FXCollections.observableArrayList();
        for (Usuario usuario : usuarios) {
            options.add(usuario.getEmailUsuario());
        }
        lista.setItems(options);
        
    }
}
