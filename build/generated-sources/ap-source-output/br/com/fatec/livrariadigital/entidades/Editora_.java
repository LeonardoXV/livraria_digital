package br.com.fatec.livrariadigital.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Editora.class)
public abstract class Editora_ {

	public static volatile SingularAttribute<Editora, String> telefone;
	public static volatile SingularAttribute<Editora, String> cidade;
	public static volatile SingularAttribute<Editora, String> estado;
	public static volatile SingularAttribute<Editora, Integer> numero;
	public static volatile SingularAttribute<Editora, String> endereco;
	public static volatile SingularAttribute<Editora, String> ddd;
	public static volatile SingularAttribute<Editora, String> bairro;
	public static volatile SingularAttribute<Editora, String> cnpj;
	public static volatile SingularAttribute<Editora, Integer> idEditora;
	public static volatile SingularAttribute<Editora, String> cep;
	public static volatile SingularAttribute<Editora, String> nomeFantasia;
	public static volatile ListAttribute<Editora, Livro> livros;
	public static volatile SingularAttribute<Editora, String> razaoSocial;

}

