/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

import java.util.Date;

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
 * @author Rubalkbas
 *
 */

@Entity
@Table(name = "gen_aux_orden_compra_detalle", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class OrdenCompraDetalle {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDEN_COMPRA_DETALLE")
	private Long idOrdenCompraDetalle;
		
	@Column(name = "ID_ORDEN_COMPRA")
	private Long idOrdenCompra;

	@Column(name = "ID_PRODUCTO")
	private Long idProducto;
	
	@Column(name = "DESCRIPCION_PRODUCTO")
	private String desPreducto;
	
	@Column(name = "PRECIO_PRODUCTO")
	private Double precioProducto;
	
	@Column(name = "TOTAL_DESCUENTO")
	private Double totalDescuento;
	
	@Column(name = "ID_MOTIVO_DESCUENTO")
	private Long idMotDescuento;
	
	@Column(name = "ESTATUS_DETALLE_PEDIDO")
	private String estatusOrdenCompra;

}
