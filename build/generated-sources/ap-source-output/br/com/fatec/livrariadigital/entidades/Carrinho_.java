package br.com.fatec.livrariadigital.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Carrinho.class)
public abstract class Carrinho_ {

	public static volatile SingularAttribute<Carrinho, Integer> idCarrinho;
	public static volatile ListAttribute<Carrinho, Livro> livros;

}

