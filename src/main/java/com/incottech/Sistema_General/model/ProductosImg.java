package com.incottech.Sistema_General.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductosImg implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idProducto;

	private String name;

	private Double oldPrice;

	private Double newPrice;

	private int discount;

	private int ratingsCount;

	private int ratingsValue;

	private String description;

	private int availibilityCount;

	private int cartCount;

	private Long categoryId;

	private int status;

	private Date fechaAlta;

	private Double weight;
	
	private int condicion;

	private Long idCatMarca;
	
	private String imagenProducto;


}
