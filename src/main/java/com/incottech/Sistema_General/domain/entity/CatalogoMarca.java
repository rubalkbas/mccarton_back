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
@Table(name = "gen_cat_marca", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class CatalogoMarca implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAT_MARCA")
	private Long idCatMarca;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "IMAGEN")
	private String imagen;
	
	@Column(name = "ESTATUS")
	private int estatus;
	
	@Column(name = "FEC_ALTA")
	private Date fechaAlta;

}
