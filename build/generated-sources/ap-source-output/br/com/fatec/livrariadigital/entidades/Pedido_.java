package br.com.fatec.livrariadigital.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Date> dataDeCompra;
	public static volatile SingularAttribute<Pedido, Usuario> usuarioInterno;
	public static volatile SingularAttribute<Pedido, Double> valorTotal;
	public static volatile ListAttribute<Pedido, ItemPedido> intensPedido;
	public static volatile SingularAttribute<Pedido, Integer> idPedido;

}

