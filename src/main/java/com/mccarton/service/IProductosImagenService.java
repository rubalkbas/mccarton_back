package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.ProductoImagen;
import com.mccarton.model.dto.RequestAltaProductoBean;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ProductosEntity;
import com.mccarton.model.entity.ProductosImagenEntity;

public interface IProductosImagenService {
	
	 SingleResponse<ProductosImagenEntity> guardarProductoImagen(RequestAltaProductoBean altaProductoImagen);
	 
	void guardarImagenes(List<ProductoImagen> imagenes, ProductosEntity producto);

	SingleResponse<List<ProductosImagenEntity>> consultarProductosImgID(ProductosEntity producto);
	
	void eliminaImagenes(ProductosEntity producto);

	SingleResponse<ProductosEntity> agregarImagen(RequestAltaProductoBean producto);

}
