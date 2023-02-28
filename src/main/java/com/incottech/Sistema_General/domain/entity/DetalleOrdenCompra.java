package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo N
 *
 */

@Entity
@Table(name = "gen_detalle_orden_compra", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class DetalleOrdenCompra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DETALLE_ORDEN_COMPRA")
	private Long idDetalleOrdenCompra;

	@Column(name = "COSTO_MOMENTO")
	private Double costoMomento;
	
	@Column(name = "ID_ORDEN_COMPRA")
	private Long idOrdenCompra;
	
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;
	
}
