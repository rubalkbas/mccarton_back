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

import com.incottech.Sistema_General.domain.entity.CarroCompras;

/**
 * @author Rubalkbas
 *
 */
@Repository
public interface CarroComprasRepository extends JpaRepository <CarroCompras, Long >{

	@Query(value = "select * from bdgeneral.gen_aux_carro_compras WHERE  ID_USUARIO = :idUsuario ", nativeQuery = true)
	List<CarroCompras> consultaCarroCompras(@Param("idUsuario") String idUsuario);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from bdgeneral.gen_aux_carro_compras WHERE  ID_USUARIO = :idUsuario ", nativeQuery = true)
	void borraCarrito(@Param("idUsuario") String idUsuario);
	
	
	
	
	
}
