/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.telasgui.controllers;

import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Pedido;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 *
 * @author Leonardo
 */
public class PedidosCadastradosController extends ControllerGeral{
        public void preencherListView(ListView lista){
        List<Pedido>pedidos=new ArrayList();
        pedidos=this.consultaBasica("pedido");
        ObservableList<String> options=FXCollections.observableArrayList();
        for (Pedido pedido : pedidos) {
            options.add(String.valueOf("PEDIDO: "+pedido.getIdPedido())+" STATUS: "+" "+pedido.getStatus()+" Valor Total R$: "+pedido.getValorTotal());
        }
        lista.setItems(options);
        
    }
    
}
