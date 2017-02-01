
package br.com.fatec.livrariadigital.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedidos")
public class Pedido implements Serializable {
    
 
    private int idPedido;
    private Date dataDeCompra;
    private List<ItemPedido> intensPedido;
    private Usuario usuarioInterno;
    private double valorTotal;
    private String status="EM ANALISE";

    public Pedido() {
    }

    @Id
    @Column(name = "N_IdPedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdPedido() {
        return idPedido;
    }
    
    @Column(name = "D_dataComp")
    public Date getDataDeCompra() {
        return dataDeCompra;
    }

    public void setDataDeCompra(Date dataDeCompra) {
        this.dataDeCompra = dataDeCompra;
    }
    
    @OneToMany
    @JoinColumn(name = "N_IdPedido")
    public List<ItemPedido> getIntensPedido() {
        return intensPedido;
    }

    public void setIntensPedido(List<ItemPedido> intensPedido) {
        this.intensPedido = intensPedido;
    }

   
    @ManyToOne
    @JoinColumn(name = "C_NomeUsua",nullable = false)
    public Usuario getUsuarioInterno() {
        return usuarioInterno;
    }

    public void setUsuarioInterno(Usuario usuarioInterno) {
        this.usuarioInterno = usuarioInterno;
    }

    @Column(name = "N_ValoPedi")
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }


    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    
    @Column(name = "C_Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public Pedido(Date dataDeCompra, List<ItemPedido> intensPedido, Usuario usuarioInterno, double valorTotal) {
        this.dataDeCompra = dataDeCompra;
        this.intensPedido = intensPedido;
        this.usuarioInterno = usuarioInterno;
        this.valorTotal = valorTotal;
    }
    
    
}
