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
@Table(name = "gen_mae_orden_compra", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class OrdenCompra implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDEN_COMPRA")
	private Long id;
		
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(name = "id_comicilio_usuario")
	private Long idDomUsuario;
		
	@Column(name = "FECHA_COMPRA")
	private Date fechaCompra;
	
	@Column(name = "TOTAL")
	private Double total;
	
	@Column(name = "IVA")
	private Double iva;
	
	@Column(name = "TOTAL_DESCUENTO")
	private int totalDescuento;
	
	@Column(name = "ID_MOTIVO_DESCUENTO")
	private Long idMotDescuento;
	
	@Column(name = "ESTATUS_ORDEN_COMPRA")
	private String estatusOrdenCompra;
	
	@Column(name = "CODIGO_RASTREO")
	private String codigoRastreo;

}
