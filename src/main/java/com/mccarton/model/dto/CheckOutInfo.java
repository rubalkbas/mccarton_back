package com.mccarton.model.dto;

import java.io.Serializable;

import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckOutInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817322963882717063L;
	
	private double totalProductos;
	
	private double iva;
	
	private double pagoTotal;
	
	private String paypalClientId;
	
	private String paypalCurrencyCode;
	
	private DireccionEntity direccion;
	
	private ResponseListarCarrito carrito;
	
	private ClienteEntity cliente;
	

}
