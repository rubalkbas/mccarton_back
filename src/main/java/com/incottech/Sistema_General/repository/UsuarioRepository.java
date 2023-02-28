package com.incottech.Sistema_General.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.Usuario;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-11-09
 */

@Repository
public interface UsuarioRepository extends JpaRepository< Usuario , Long > {
	
	Usuario findByCorreoIgnoreCase(String correo);
	
	@Query(value = "select * from bdgeneral.gen_mae_usuarios WHERE  CORREO = :correo ", nativeQuery = true)
	Usuario usuarioHabilitado(@Param("correo") String correo);
	
	@Query(value = "SELECT * FROM bdgeneral.gen_mae_usuarios  WHERE correo = :usuario and estatus = :estatus", nativeQuery = true)
	Optional<Usuario> obtenerPorIdEmpleado(String usuario, String estatus);

}
