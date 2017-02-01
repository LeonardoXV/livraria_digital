package br.com.fatec.livrariadigital.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Autor.class)
public abstract class Autor_ {

	public static volatile SingularAttribute<Autor, Date> dataFale;
	public static volatile SingularAttribute<Autor, String> localMorte;
	public static volatile SingularAttribute<Autor, Integer> idAutor;
	public static volatile SingularAttribute<Autor, Date> dataNasce;
	public static volatile ListAttribute<Autor, Livro> livros;
	public static volatile SingularAttribute<Autor, String> biografia;
	public static volatile SingularAttribute<Autor, String> localNasc;
	public static volatile SingularAttribute<Autor, String> nome;

}

