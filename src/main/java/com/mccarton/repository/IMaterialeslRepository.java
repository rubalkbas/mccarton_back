package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.MaterialesEntity;

public interface IMaterialeslRepository extends JpaRepository<MaterialesEntity, Integer>{
	
	Optional<MaterialesEntity> findByNombreMaterialIgnoreCase(String nombreMaterial);
	List<MaterialesEntity> findByEstatus(Integer estatus);

}
