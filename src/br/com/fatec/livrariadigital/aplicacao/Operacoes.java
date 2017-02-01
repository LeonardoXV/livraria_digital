
package br.com.fatec.livrariadigital.aplicacao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public abstract class Operacoes {
    private  EntityManagerFactory entityManagerFactory;
    private  EntityManager entityManager;
    private List elementos; 
    private Query consulta;
    private String entidade;

    public Operacoes(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager=entityManagerFactory.createEntityManager();
    }
    public abstract void deletar(Serializable serializable); 
    public abstract void atualizar(Serializable serializable);
    public abstract void gerarImformacao();
    public abstract void retirarInformacoes();

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager=this.entityManagerFactory.createEntityManager();
    }
    
    
    
     public void cadastrarNoBanco(Serializable serializable) throws RuntimeException {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(serializable);
        this.entityManager.getTransaction().commit();
        this.gerarImformacao();
    }
     
     public void cadastrarNoBancoCarrinho(Serializable serializable) throws RuntimeException {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(serializable);
        this.entityManager.getTransaction().commit();
    }
     
           public List consultarLista(String sql){
            this.entityManager.getTransaction().begin();
            this.consulta=this.entityManager.createQuery(sql);
            this.elementos=this.consulta.getResultList();
            this.entityManager.getTransaction().commit();
            return this.elementos;
       }
           
               public List consultaComLike(String titulo){
            this.entityManager.getTransaction().begin();
            this.consulta=this.entityManager.createQuery("from Livro where titulo like :titulo");
            this.consulta.setParameter("titulo","%"+titulo+"%");
            this.elementos=this.consulta.getResultList();
            this.entityManager.getTransaction().commit();
            return this.elementos;
    }
       
               

}
