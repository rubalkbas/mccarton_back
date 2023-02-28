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
@Table(name = "gen_mae_producto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class MaestraProducto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "OLD_PRICE")
	private Double oldPrice;
	
	@Column(name = "NEW_PRICE")
	private Double newPrice;
	
	@Column(name = "DISCOUNT")
	private int discount;
	
	@Column(name = "RATINGS_COUNT")
	private int ratingsCount;
	
	@Column(name = "RATINGS_VALUE")
	private int ratingsValue;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "AVAILIBILITY_COUNT")
	private int availibilityCount;
	
	@Column(name = "CART_COUNT")
	private int cartCount;
	
	@Column(name = "CATEGORY_ID")
	private Long categoryId;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "FEC_ALTA")
	private Date fechaAlta;
	
	@Column(name = "WEIGHT")
	private Double weight;
	
	@Column(name = "CONDICION")
	private int condicion;
	
	@Column(name = "ID_CAT_MARCA")
	private Long idCatMarca;

}
