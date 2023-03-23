package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.ListaDeseosProductosDTO;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ListaDeseosEntity;

public interface IListaDeseosService {

	SingleResponse<List<ListaDeseosProductosDTO>> consultarTodos(Integer idCliente);
	SingleResponse<ListaDeseosEntity> guardarListaDeseo(Integer idCliente, Integer idProducto);
	SingleResponse<ListaDeseosEntity> eliminarListaDeseo(Integer idListaDeseo);
	
}
