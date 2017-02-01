package br.com.fatec.livrariadigital.entidades;

import java.io.File;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Livro.class)
public abstract class Livro_ {

	public static volatile SingularAttribute<Livro, Detalhes> detahes;
	public static volatile ListAttribute<Livro, ItemPedido> itens;
	public static volatile SingularAttribute<Livro, Double> precoDeVenda;
	public static volatile SingularAttribute<Livro, String> isbn;
	public static volatile ListAttribute<Livro, Categoria> categorias;
	public static volatile SingularAttribute<Livro, Double> precoDeCusto;
	public static volatile SingularAttribute<Livro, Integer> idLivro;
	public static volatile SingularAttribute<Livro, File> imagem;
	public static volatile SingularAttribute<Livro, String> titulo;
	public static volatile ListAttribute<Livro, Autor> autores;
	public static volatile SingularAttribute<Livro, Editora> editora;
	public static volatile SingularAttribute<Livro, Integer> quantidade_livro;

}

