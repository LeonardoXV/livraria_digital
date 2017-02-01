
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Informacoes;
import br.com.fatec.livrariadigital.entidades.ItemPedido;
import br.com.fatec.livrariadigital.entidades.Livro;
import br.com.fatec.livrariadigital.entidades.Pedido;
import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.entidades.UsuarioLogado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class OperacoesPedido  {
    
    private List<ItemPedido> itens=new ArrayList();
    private double valorTotal;
    private EntityManagerFactory factory=Persistence.createEntityManagerFactory("persistence");
    private EntityManager entity;
    OperacoesLogado logado=new OperacoesLogado();
    
    public OperacoesPedido() {
     this.entity=this.factory.createEntityManager();
    }

    public void finalizarPedido(List<ItemPedido> itens){
        this.itens=itens;
        Pedido pedido=new Pedido(new Date(), itens, this.logado.obterLogado(), this.calcularTotal());
        this.entity.getTransaction().begin();
        this.entity.persist(pedido);
        for (ItemPedido iten : itens) {
            iten.setPedido(pedido);
            this.entity.persist(iten);
        }
        this.gerarInformacao();
        this.entity.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "Pedido Finalizado");
    }
    
    private double calcularTotal(){
        for (ItemPedido iten : this.itens) {
            this.valorTotal+=iten.getLivro().getPrecoDeVenda()*iten.getQuantidade();
        }
        return this.valorTotal;
    }
    
    private  void gerarInformacao(){
        Informacoes in=this.entity.find(Informacoes.class,0);
        in.setNumPedidos(in.getNumPedidos()+1);
    }
}
