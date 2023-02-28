/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

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
 * @author Rubalkbas
 *
 */
@Entity
@Table(name = "gen_aux_carro_compras", schema = "bdgeneral")
@Getter
@Setter
@NoArgsConstructor
public class CarroCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARRITO")
	private Long idCarrito;
	
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(name = "ID_PRODUCTO")
	private Long idProducto;
	
	@Column(name = "cantidad")
	private int cantidad;

}
