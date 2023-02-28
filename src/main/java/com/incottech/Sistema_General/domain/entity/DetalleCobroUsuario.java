package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
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
 * @author Eduardo N
 *
 */

@Entity
@Table(name = "gen_detalle_cobro_usuario", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class DetalleCobroUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DETALLE_COBRO_USUARIO")
	private Long idDetalleCobroUsuario;

	@Column(name = "DESCUENTO")
	private Double descuento;
	
	@Column(name = "RAZON_DESCUENTO")
	private String razonDescuento;
	
	@Column(name = "COSTO_MOMENTO_VENTA")
	private Double costoMomentoVenta;
	
	@Column(name = "COSTO_MOMENTO_COMPRA")
	private Double costoMomentoCompra;
	
	@Column(name = "ESTATUS")
	private Long estatus;
	
	@Column(name = "ID_TARIFA")
	private Long idTarifa;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

}
