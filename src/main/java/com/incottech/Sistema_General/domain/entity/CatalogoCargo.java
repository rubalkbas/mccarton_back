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
@Table(name = "gen_cat_cargo", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class CatalogoCargo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CAT_CARGO")
	private Long idCatCargo;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ESTATUS")
	private Long estatus;

}
