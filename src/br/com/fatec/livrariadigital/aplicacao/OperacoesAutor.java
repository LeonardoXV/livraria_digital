
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Autor;
import br.com.fatec.livrariadigital.entidades.Informacoes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class OperacoesAutor extends Operacoes {
  
    private List elementos; 
    private Query consulta;
    public OperacoesAutor(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }


    public void deletar(Serializable serializable) {
         Autor autor=(Autor) serializable;
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(this.getEntityManager().getReference(autor.getClass(),autor.getIdAutor()));
         this.getEntityManager().getTransaction().commit();
    }


    public void atualizar(Serializable serializable) {
        Autor autor=(Autor) serializable;
        this.getEntityManager().getTransaction().begin();
        Autor a=this.getEntityManager().find(autor.getClass(), autor.getIdAutor());
        a.setLocalNasc(autor.getLocalNasc());
        a.setBiografia(autor.getBiografia());
        a.setLocalMorte(autor.getLocalMorte());
        a.setDataFale(autor.getDataFale());
        a.setDataNasce(autor.getDataNasce());
        this.getEntityManager().merge(a);
        this.getEntityManager().getTransaction().commit();
    }


        public List consultaComLikeAutor(String nome){
            this.getEntityManager().getTransaction().begin();
            this.consulta=this.getEntityManager().createQuery("from Autor where nome like :nome");
            this.consulta.setParameter("nome","%"+nome+"%");
            this.elementos=this.consulta.getResultList();
            this.getEntityManager().getTransaction().commit();
            return this.elementos;
    }

    @Override
    public void gerarImformacao() {
    }

    @Override
    public void retirarInformacoes() {   
    }
    
}
