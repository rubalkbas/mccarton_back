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
 * @author Eduardo N
 *
 */

@Entity
@Table(name = "gen_cat_motivo_descuento", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class CatMotivoDescuento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAT_MOTIVO_DESCUENTO")
	private Long idCatMotivoDescuento;
		
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "FECHA_DESDE")
	private Date fechaDesde;
	
	@Column(name = "FECHA_HASTA")
	private Date fechaHasta;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Column(name = "TIPO_DESCUENTO")
	private int tipoDescuento;
	
	@Column(name = "PORCENTAJE_DESCUENTO")
	private int porcentajeDescuento;
	
	@Column(name = "ID_USUARIO_ALTA")
	private Long idUsuarioAlta;
}
