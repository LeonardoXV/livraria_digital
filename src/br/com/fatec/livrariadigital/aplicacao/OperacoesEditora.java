
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Editora;
import br.com.fatec.livrariadigital.entidades.Informacoes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class OperacoesEditora extends Operacoes {
        private List elementos; 
    private Query consulta;
    private String entidade;

    public OperacoesEditora(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
   public void deletar(Serializable serializable) {
      Editora editora=(Editora) serializable;
      this.getEntityManager().getTransaction().begin();
      this.getEntityManager().remove(this.getEntityManager().getReference(editora.getClass(),editora.getIdEditora()));
      this.getEntityManager().getTransaction().commit();
      this.retirarInformacoes();
    }

    @Override
    public void atualizar(Serializable serializable) {
        Editora editora=(Editora) serializable;
        this.getEntityManager().getTransaction().begin();
        Editora a=this.getEntityManager().find(editora.getClass(), editora.getIdEditora());
        a.setBairro(editora.getBairro());
        a.setCep(editora.getCep());
        a.setCidade(editora.getCidade());
        a.setCnpj(editora.getCnpj());
        a.setDdd(editora.getDdd());
        a.setEndereco(editora.getEndereco());
        a.setNomeFantasia(editora.getNomeFantasia());
        a.setNumero(editora.getNumero());
        a.setRazaoSocial(editora.getRazaoSocial());
        a.setTelefone(editora.getTelefone());
        a.setEstado(editora.getEstado());
        this.getEntityManager().merge(a);
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public void gerarImformacao() {
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumEditoras(informacoes.getNumEditoras()+1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();

    }
    @Override
    public void retirarInformacoes(){
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumEditoras(informacoes.getNumEditoras()-1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();
    }
    
    

     public List consultaLikeEditora(String nomeFantasia){
         this.getEntityManager().getTransaction().begin();
         consulta=this.getEntityManager().createQuery("from Editora where nomeFantasia like :nomeFantasia");
         consulta.setParameter("nomeFantasia","%"+nomeFantasia+"%");
         elementos=this.consulta.getResultList();
         this.getEntityManager().getTransaction().commit();
        return this.elementos;
    }
   
    
}
