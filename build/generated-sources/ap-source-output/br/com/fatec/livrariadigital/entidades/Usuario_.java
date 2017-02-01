package br.com.fatec.livrariadigital.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> cidade;
	public static volatile SingularAttribute<Usuario, String> estado;
	public static volatile SingularAttribute<Usuario, String> tipo;
	public static volatile SingularAttribute<Usuario, String> endereco;
	public static volatile SingularAttribute<Usuario, Integer> numero;
	public static volatile SingularAttribute<Usuario, String> bairro;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile ListAttribute<Usuario, Pedido> pedidos;
	public static volatile SingularAttribute<Usuario, String> sobreNome;
	public static volatile SingularAttribute<Usuario, String> cep;
	public static volatile SingularAttribute<Usuario, String> senha;
	public static volatile SingularAttribute<Usuario, Date> dataNasc;
	public static volatile SingularAttribute<Usuario, String> cpf;
	public static volatile SingularAttribute<Usuario, String> emailUsuario;
	public static volatile SingularAttribute<Usuario, Character> sexo;

}

