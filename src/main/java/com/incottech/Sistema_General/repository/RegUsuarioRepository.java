/**
 * 
 */
package com.incottech.Sistema_General.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incottech.Sistema_General.domain.entity.RegUsuarios;

/**
 * @author Rubén Vazquez Acosta
 *
 */
public interface RegUsuarioRepository extends JpaRepository<RegUsuarios, Long>{

	RegUsuarios findByCorreoIgnoreCase(String correo);
	
	@Query(value = "select * from bdgeneral.gen_mae_usuarios WHERE  CORREO = :correo ", nativeQuery = true)
	RegUsuarios usuarioHabilitado(@Param("correo") String correo);
	
	
}
