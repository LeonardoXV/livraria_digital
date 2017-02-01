
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Carrinho;
import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Informacoes;
import br.com.fatec.livrariadigital.entidades.Livro;
import java.io.Serializable;
import javax.persistence.EntityManagerFactory;

public class OperacoesLivro extends Operacoes {

    public OperacoesLivro(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public void deletar(Serializable serializable) {
        Livro livro=(Livro) serializable;
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().remove(this.getEntityManager().getReference(livro.getClass(),livro.getIdLivro()));
        this.getEntityManager().getTransaction().commit();
        this.retirarInformacoes();
    }

    @Override
    public void atualizar(Serializable serializable) {
        Livro livro=(Livro) serializable;
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(livro);
        this.getEntityManager().getTransaction().commit();
        this.getEntityManager().close();
       
    }

    @Override
    public void gerarImformacao() {
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumLivros(informacoes.getNumLivros()+1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();
    }
    
    @Override
    public void retirarInformacoes(){
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumLivros(informacoes.getNumLivros()-1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();
    }
    
    public void deletarCarrinho(Carrinho carrinho){
         this.getEntityManager().getTransaction().begin();
        this.getEntityManager().remove(this.getEntityManager().getReference(carrinho.getClass(),carrinho.getIdCarrinho()));
        this.getEntityManager().getTransaction().commit();
    }
    
}
