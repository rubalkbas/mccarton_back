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
@Table(name = "gen_aux_img_producto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class AuxiliarImgProducto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMG_PRODUCTO")
	private Long idImgProducto;

	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

	@Column(name = "SMALL")
	private String small;
	
	@Column(name = "MEDIUM")
	private String medium;
	
	@Column(name = "BIG")
	private String big;

}
