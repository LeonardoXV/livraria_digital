/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Informacoes;
import br.com.fatec.livrariadigital.entidades.Usuario;
import java.io.Serializable;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author r2d2
 */
public class OperacoesUsuarioInterno extends Operacoes{

    public OperacoesUsuarioInterno(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public void deletar(Serializable serializable) {
        Usuario usuario=(Usuario) serializable;
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().remove(this.getEntityManager().getReference(usuario.getClass(),usuario.getEmailUsuario()));
         this.getEntityManager().getTransaction().commit();
         this.retirarInformacoes();
    }

    @Override
    public void atualizar(Serializable serializable) {
        Usuario usuario=(Usuario) serializable;
        this.getEntityManager().getTransaction().begin();
        Usuario us=this.getEntityManager().find(usuario.getClass(), usuario.getEmailUsuario());
        us.setBairro(usuario.getBairro());
        us.setCep(usuario.getCep());
        us.setCidade(usuario.getCidade());
        us.setDataNasc(usuario.getDataNasc());
        us.setEndereco(usuario.getEndereco());
        us.setEstado(usuario.getEstado());
        us.setNome(usuario.getNome());
        us.setNumero(usuario.getNumero());
        us.setSenha(usuario.getSenha());
        us.setSexo(usuario.getSexo());
        us.setSobreNome(usuario.getSobreNome());
        us.setCpf(usuario.getCpf());
        this.getEntityManager().merge(us);
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public void gerarImformacao() {
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumUsuarios(informacoes.getNumUsuarios()+1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();
    
    }
    @Override
    public void retirarInformacoes(){
        Informacoes informacoes=new Informacoes();
        this.getEntityManager().getTransaction().begin();
        informacoes=this.getEntityManager().find(Informacoes.class,0);
        informacoes.setNumUsuarios(informacoes.getNumUsuarios()-1);
        this.getEntityManager().merge(informacoes);
        this.getEntityManager().getTransaction().commit();
    
    }
    
}
