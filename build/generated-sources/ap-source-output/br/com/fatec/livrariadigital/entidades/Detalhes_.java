package br.com.fatec.livrariadigital.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Detalhes.class)
public abstract class Detalhes_ {

	public static volatile SingularAttribute<Detalhes, String> indice;
	public static volatile SingularAttribute<Detalhes, String> formato;
	public static volatile SingularAttribute<Detalhes, Integer> idDetalhes;
	public static volatile SingularAttribute<Detalhes, String> resumo;
	public static volatile SingularAttribute<Detalhes, Date> dataDePublicacao;
	public static volatile SingularAttribute<Detalhes, Integer> numPaginas;

}

