package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import com.mccarton.model.entity.ProductosEntity;
import com.mccarton.model.entity.ProductosImagenEntity;

public interface IProductosImagenRepository extends JpaRepository<ProductosImagenEntity, Integer>{
	
	List<ProductosImagenEntity> findByIdProducto(ProductosEntity producto);



}
