package com.mccarton.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.CategoriasEntity;


public interface ICategoriaRepository extends JpaRepository<CategoriasEntity, Integer> {

	Optional<CategoriasEntity> findByNombreCategoriaIgnoreCase(String nombreCategoria);	

	List<CategoriasEntity> findByEstatus(Integer estatus);
}
