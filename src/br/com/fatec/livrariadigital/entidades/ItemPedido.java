
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tb_ItemPedido")
public class ItemPedido implements Serializable {
    
    private int idItemPedido;
    private int quantidade;
    private Livro livro;
    private Pedido pedido;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdItemPedido() {
        return idItemPedido;
    }
    
    

    public void setIdItemPedido(int idItemPedido) {
        this.idItemPedido = idItemPedido;
    }
    
    @Column(name = "N_qtdPedido")
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    @ManyToOne
    @JoinColumn(name = "N_idLivro")
    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public ItemPedido(int quantidade, Livro livro) {
        this.quantidade = quantidade;
        this.livro = livro;
    }

    @ManyToOne
    @JoinColumn(name = "N_IdPedido",nullable = false)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    
    
    
    
    
    
}
