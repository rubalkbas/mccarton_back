package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.CategoriasEntity;


public interface ICategoriaRepository extends JpaRepository<CategoriasEntity, Integer> {

}
