package com.mccarton.model.dto;

import java.time.LocalDateTime;
import java.util.List;


import com.mccarton.model.entity.ProductosEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaDeseosProductosDTO {
	
	
	private Integer idListaDeseo;
	
	private LocalDateTime fecha;
	
	private ProductosEntity producto;

}
