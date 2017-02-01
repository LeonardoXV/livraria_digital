
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.ItemPedido;
import br.com.fatec.livrariadigital.entidades.Livro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.script.ScriptEngine;


public class CarrinhoDeCompras {
    
   // private List<ItemPedido>itensPedido=new ArrayList();
    private Livro livro;
    private List<Livro>livrosPedido = new ArrayList();

    public List<Livro> getLivrosPedido() {
        return livrosPedido;
    }

    public void setLivrosPedido(List<Livro> livrosPedido) {
        this.livrosPedido = livrosPedido;
    }
    
    public void adicionarLivro(Livro livro){
        livrosPedido.add(livro);
        System.out.println("Livro adicionado");
    }
    
    public void removerLivro(Livro livro){
        livrosPedido.remove(livro);
    }
    
    /*public void adicionarAoCarrinho(ItemPedido item){
        this.itensPedido.add(item);
    }
    
    public void removerDoCarrinho(ItemPedido item){
        this.itensPedido.remove(item);
    }*/
    
}
