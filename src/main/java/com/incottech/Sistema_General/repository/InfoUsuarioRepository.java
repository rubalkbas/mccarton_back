/**
 * 
 */
package com.incottech.Sistema_General.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incottech.Sistema_General.domain.entity.InfoUsuario;

/**
 * @author Rubén Vazquez Acosta
 *
 */
public interface InfoUsuarioRepository extends JpaRepository<InfoUsuario,Long> {
	
	@Query(value = "select * from bdgeneral.gen_aux_info_usuario where ID_USUARIO = :idUsuario", nativeQuery = true)
	InfoUsuario consultaIdUsuario(@Param("idUsuario") Long idUsuario);
	
	

}
