package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Editora;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;


public class EditorasCadastradasController extends ControllerGeral {
    
    public void preencherListView(ListView lista){
        List<Editora> editoras=new ArrayList();
        editoras=this.consultaBasica("editora");
        ObservableList<String> options=FXCollections.observableArrayList();
        for (Editora editora : editoras) {
            options.add(editora.getNomeFantasia());
        }
        lista.setItems(options);
        
    }
    

    
}
