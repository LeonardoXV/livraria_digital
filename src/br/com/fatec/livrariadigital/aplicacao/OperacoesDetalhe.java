/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatec.livrariadigital.aplicacao;

import br.com.fatec.livrariadigital.entidades.Detalhes;
import java.io.Serializable;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author r2d2
 */
public class OperacoesDetalhe extends Operacoes {

    public OperacoesDetalhe(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public void deletar(Serializable serializable) {
        Detalhes detalhe=(Detalhes) serializable;
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().remove(this.getEntityManager().getReference(detalhe.getClass(),detalhe.getIdDetalhes()));
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public void atualizar(Serializable serializable) {
        this.getEntityManager().getTransaction().begin();
        Detalhes detalhe=(Detalhes) serializable;
        Detalhes d=this.getEntityManager().find(detalhe.getClass(), detalhe.getIdDetalhes());
        d.setFormato(detalhe.getFormato());
        this.getEntityManager().merge(d);
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public void gerarImformacao() {}
    @Override
    public void retirarInformacoes(){}
    
}
