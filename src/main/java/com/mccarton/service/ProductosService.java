package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ProductosEntity;
import com.mccarton.repository.IProductosRepository;

@Service
public class ProductosService implements IProductosService{
	
	
	private static final Logger log = LoggerFactory.getLogger(ProductosService.class);
	
	@Autowired
	private IProductosRepository productoRepository;


	@Transactional
	@Override
	public SingleResponse<List<ProductosEntity>> consultarProductos() {
		List<ProductosEntity> listaProductos = new ArrayList<>();
		
		try {
			listaProductos = productoRepository.findAll();
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los Productos en la BD");
		}
		
		if(!listaProductos.isEmpty()) {
			SingleResponse<List<ProductosEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de Productos exitosamente");
			response.setResponse(listaProductos);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de Productos en la BD");
	}


	@Transactional
	@Override
	public SingleResponse<ProductosEntity> crearProductos(ProductosEntity producto) {
		Optional<ProductosEntity> productoOp = Optional.empty();
		try {
			productoOp = productoRepository.findByNombreProductoIgnoreCase(producto.getNombreProducto());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los Productos en la BD");
		}
		if(productoOp.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  producto " + producto.getNombreProducto() +" ya existe en la BD");
		}
		
		try {
			producto = productoRepository.save(producto);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los Productos en la BD");
		}
		SingleResponse<ProductosEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado el producto " + producto.getNombreProducto() +" exitosamente.");
		response.setResponse(producto);
		return response;
	}


	@Override
	public SingleResponse<List<ProductosEntity>> consultarProductosStock() {
		List<ProductosEntity> listaProductos = new ArrayList<>();
		
		try {
			listaProductos = productoRepository.findByStockGreaterThan(0);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los Productos en la BD");
		}
		
		if(!listaProductos.isEmpty()) {
			SingleResponse<List<ProductosEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de Productos en Stock exitosamente");
			response.setResponse(listaProductos);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de Productos en Stock en la BD");
	}

}
