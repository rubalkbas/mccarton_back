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
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Entity
@Table(name = "gen_aux_resena_producto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class AuxResenaProducto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RESENA_PRODUCTO")
	private Long idResenaProducto;

	@Column(name = "ID_PRODUCTO")
	private Long idProducto;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name = "COMENTARIO")
	private String comentario;
	
	@Column(name = "CALIFICACION")
	private int calificacion;
	
	@Column(name = "ESTATUS")
	private int estatus;
	
	@Column(name = "FEC_ALTA")
	private Date fechaAlta;

}
