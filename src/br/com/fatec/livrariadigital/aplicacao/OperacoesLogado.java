/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Usuario;
import br.com.fatec.livrariadigital.entidades.UsuarioLogado;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leonardo
 */
public class OperacoesLogado {
    
    private EntityManagerFactory factory=Persistence.createEntityManagerFactory("persistence");
    private EntityManager entity=factory.createEntityManager();
    
   public void cadastraLogado(Usuario usuario){
        this.entity.getTransaction().begin();
        this.entity.persist(new UsuarioLogado(1, usuario));
        this.entity.getTransaction().commit();
    }
   
   public void removeLogadoAnterior(){
       this.entity.getTransaction().begin();
       this.entity.remove(this.entity.find(UsuarioLogado.class,1));
       this.entity.getTransaction().commit();
   }
   
   public Usuario obterLogado(){
       this.entity.getTransaction().begin();
       Usuario logado=this.entity.find(UsuarioLogado.class,1).getUsuario();
       this.entity.getTransaction().commit();
       return logado;
   }
    
}
