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
@Table(name = "gen_contacto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class Contacto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CONTACTO")
	private Long idContacto;

	@Column(name = "TELEFONO")
	private int telefono;
	
	@Column(name = "ESTATUS")
	private Long estatus;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Column(name = "ID_USUARIO_PROVEEDOR")
	private Long idUsuarioProveedor;

}
