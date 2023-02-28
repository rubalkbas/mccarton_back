package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class ProductoImagen implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idProducto;

	private String name;
	
	private List<?> images;
	
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
	
	private List<?> color;
	
	private List<?> size;
	
	private Double weight;
	
	private int condicion;
	
	private Long idCatMarca;

}
