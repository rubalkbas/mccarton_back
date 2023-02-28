/**
 * 
 */
package com.incottech.Sistema_General.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incottech.Sistema_General.domain.entity.UsuariosInfo;


/**
 * @author Rubén Vazquez Acosta
 *
 */
public interface UsuariosInfoRepository extends JpaRepository<UsuariosInfo,Long>{
	@Query(value = "Select * from gen_aux_usuario_info where id_usuario = :id", nativeQuery = true)
	 UsuariosInfo getUsuario(@Param("id") Long id);
	
	
}
