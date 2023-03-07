package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.RolEntity;

public interface IRolRepository extends JpaRepository<RolEntity, Integer>{
	
	Optional<RolEntity> findByNombreRolIgnoreCase(String nombreRol);
	List<RolEntity> findByEstatus(Integer estatus);

}
