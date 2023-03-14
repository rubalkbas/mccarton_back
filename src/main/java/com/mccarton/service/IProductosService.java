package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ProductosEntity;

public interface IProductosService {
	
	SingleResponse<List<ProductosEntity>> consultarProductos();
	SingleResponse<ProductosEntity> crearProductos(ProductosEntity rol);
	SingleResponse<List<ProductosEntity>> consultarProductosStock();
}
