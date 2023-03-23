package com.mccarton.model.dto;

import java.io.Serializable;
import java.util.List;

import com.mccarton.model.entity.CarroComprasEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseListarCarrito implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1584539341046501491L;

	private double totalEstimado;
	
	private List<CarroComprasEntity> carrito;
}
