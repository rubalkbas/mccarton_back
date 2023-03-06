package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.OrdenDetalleEntity;

public interface IOrdenDetalleRepository extends JpaRepository<OrdenDetalleEntity, Integer> {

}
