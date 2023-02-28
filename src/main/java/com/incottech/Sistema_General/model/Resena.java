package com.incottech.Sistema_General.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

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
public class Resena implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idResenaProducto;

	private Long idUsuario;
	
	private String nombre;
	
	private Long idProducto;
	
	private String nombreProducto;
	
	private String imagenProducto;

	private String comentario;

	private int calificacion;

	private int estatus;

	private Date fechaAlta;

}
