/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.entidades;

import javafx.beans.property.SimpleStringProperty;


public class teste {
    private SimpleStringProperty numero;
    private SimpleStringProperty data;
    private SimpleStringProperty valor;
    private SimpleStringProperty preco;

    public teste(String numero, String data, String valor,String preco) {
        this.numero = new SimpleStringProperty(numero) ;
        this.data = new SimpleStringProperty(data);
        this.valor = new SimpleStringProperty(valor);
        this.preco=new SimpleStringProperty(preco);
    }

    public String getNumero() {
        return numero.get();
    }

    public void setNumero(String numero) {
        this.numero = new SimpleStringProperty(numero) ;
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
         this.data = new SimpleStringProperty(data);
    }

    public String getValor() {
        return valor.get();
    }

    public void setValor(String valor) {
        this.valor = new SimpleStringProperty(valor);
    }

    public String getPreco() {
        return preco.get();
    }

    public void setPreco(String preco) {
        this.preco=new SimpleStringProperty(preco);
    }
    
    
    
}
