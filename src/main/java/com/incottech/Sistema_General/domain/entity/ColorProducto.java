/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

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
 * @author Rubén Vazquez Acosta
 *
 */
@Entity
@Table(name = "gen_aux_color_producto", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class ColorProducto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COLOR_PRODUCTO")
	private Long idCobroUsuario;
	
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

	@Column(name = "CODIGO_COLOR")
	private String codigoColor;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "STATUS")
	private String status;
	

}
