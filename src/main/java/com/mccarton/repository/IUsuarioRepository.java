package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mccarton.model.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

	Optional<UsuarioEntity> findByCorreoElectronicoIgnoreCase(String correo);
	List<UsuarioEntity> findByEstatusOrderByApellidoPaternoAscApellidoMaternoAscNombreUsuarioAsc(Integer estatus);
}
