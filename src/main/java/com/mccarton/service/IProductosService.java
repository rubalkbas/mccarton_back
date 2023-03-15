package com.mccarton.service;

import java.util.List;
import java.util.Optional;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ProductosEntity;
import com.mccarton.model.entity.RolEntity;

public interface IProductosService {
	
	SingleResponse<List<ProductosEntity>> consultarProductos();
	SingleResponse<ProductosEntity> crearProductos(ProductosEntity producto);
	SingleResponse<List<ProductosEntity>> consultarProductosStock();
	SingleResponse<Optional<ProductosEntity>> detalleProducto(ProductosEntity producto);
	SingleResponse<ProductosEntity> actualizarProducto(ProductosEntity producto);

}
