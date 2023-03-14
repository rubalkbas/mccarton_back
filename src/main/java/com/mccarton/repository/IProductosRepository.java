package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ProductosEntity;

public interface IProductosRepository extends JpaRepository<ProductosEntity, Integer>{
	
	Optional<ProductosEntity> findByNombreProductoIgnoreCase(String nombreProducto);
	List<ProductosEntity> findByStockGreaterThan(Integer cantidad);

}
