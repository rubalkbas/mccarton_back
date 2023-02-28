/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * @author Rubén Vazquez Acosta
 *
 */
@Entity
@Table(name = "gen_mae_producto_completo", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class ProductoCompleto {

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


	
}
