package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ProductosEntity;

public interface IProductoRepository  extends JpaRepository<ProductosEntity, Integer>  {

}
