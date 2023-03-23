package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.UsuarioEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	Optional<ClienteEntity> findBycorreoElectronicoIgnoreCase(String id);
	List<ClienteEntity> findByEstatusOrderByApellidoPaternoAscApellidoMaternoAscNombreAsc(Integer estatus);
	Page<ClienteEntity> findByEstatus(Integer estatus, Pageable pageable);
	@Query("SELECT u FROM ClienteEntity u WHERE CONCAT( u.nombre,' ',u.apellidoPaterno,' ',"
			+ "u.apellidoMaterno,' ' , u.correoElectronico) LIKE %?1%")		
	Page<ClienteEntity> findAll(String search, Pageable pageable);
	Optional<ClienteEntity> findByCorreoElectronicoIgnoreCaseAndEstatus(String correo, Integer estatus);
}
