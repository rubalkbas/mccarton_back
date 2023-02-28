package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author Mauricio Soto
 * @version 1.0
 * @since   2021-01-06
 */ 
@Entity
@Immutable
@Table(name = "view_lista_deseo_producto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class ListaDeseoProducto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_producto")
	private Long idProducto;

	@Column(name= "availibility_count")
	private int availabilityAcount;
	
	@Column(name= "cart_count")
	private int cartCount;
	
	@Column(name= "category_id")
	private Long categoryId;
	
	@Column(name= "description")
	private String description;
	
	@Column(name= "discount")
	private int discount;
	
	@Column(name= "fec_alta")
	private Date fecAlta;
	 
	@Column(name= "name")
	private String name;
	
	@Column(name= "new_price")
	private double newPrice;
	
	@Column(name= "old_price")
	private double oldPrice;
	
	@Column(name= "ratings_count")
	private int ratingsCount;
	
	@Column(name= "ratings_value")
	private int ratingsValue;
	
	@Column(name= "status")
	private int status;
	
	@Column(name= "weight")
	private double weight;
	
	@Column(name= "id_lista_deseo")
	private Long idListaDeseo;
	
	@Column(name= "id_usuario")
	private Long idUsuario;
	
	@Column(name= "id_img_producto")
	private Long idImgProducto;
	
	@Column(name= "small")
	private String small;
	
	@Column(name= "medium")
	private String medium;
	
	@Column(name= "big")
	private String big;
	
}
