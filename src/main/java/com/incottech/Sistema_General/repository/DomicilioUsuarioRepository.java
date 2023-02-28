/**
 * 
 */
package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.domicilioUsuario;

/**
 * @author Rubalkbas
 *
 */
@Repository
public interface DomicilioUsuarioRepository extends JpaRepository <domicilioUsuario, Long>{
	
	@Query(value = "select * from bdgeneral.gen_aux_domicilio_usuario where id_usuario = :idUsuario and estatus = 1", nativeQuery = true)
	List <domicilioUsuario> consultaDomicilioUsuario(@Param("idUsuario") Long idUsuario);

	
	@Query(value = "select * from bdgeneral.gen_aux_domicilio_usuario where id_usuario = :idUsuario and estatus = 1 and domicilio_predeterminado = 1", nativeQuery = true)
	List <domicilioUsuario> consultaDomPredeUsuario(@Param("idUsuario") Long idUsuario);
	
	@Query(value = "select * from bdgeneral.gen_aux_domicilio_usuario where id_comicilio_usuario = :idDomicilioUsuario", nativeQuery = true)
	List <domicilioUsuario> consultaDomOrden(@Param("idDomicilioUsuario") Long idDomicilioUsuario);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update gen_aux_domicilio_usuario \r\n" + 
			"set domicilio_predeterminado = 0 where id_usuario = :idUsuario ", nativeQuery = true)
	void actualizaCeroDomiPrede(@Param("idUsuario") Long idUsuario);

	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update gen_aux_domicilio_usuario \r\n" + 
			"set domicilio_predeterminado = 1 where id_comicilio_usuario = :idDomi and id_usuario = :idUsuario ", nativeQuery = true)
	void guardaDomPredeUsuario(@Param("idUsuario") Long idUsuario, @Param("idDomi") Long idDomi);
	
	

}
