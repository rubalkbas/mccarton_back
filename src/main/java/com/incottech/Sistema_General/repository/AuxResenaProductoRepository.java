package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.AuxResenaProducto;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface AuxResenaProductoRepository extends JpaRepository< AuxResenaProducto , Long > {
	
	@Query(value = "select * "
					+ "from bdgeneral.gen_aux_resena_producto "
					+ "where estatus = 1 and id_producto = :idProducto order by id_resena_producto desc", nativeQuery = true)
	List <AuxResenaProducto> consultaResenaPorIdProducto(@Param("idProducto") Long idProducto);
	
	@Query(value = "select estatus from bdgeneral.gen_aux_resena_producto where id_resena_producto = :idResenaProducto", nativeQuery = true)
	 String valorEstatusResena(@Param("idResenaProducto") int idResenaProducto);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_aux_resena_producto set estatus = 0 WHERE id_resena_producto = :idResenaProducto", nativeQuery = true)
	void editaEstatusInactivo(@Param("idResenaProducto") int idResenaProducto);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_aux_resena_producto set estatus = 1 WHERE id_resena_producto = :idResenaProducto", nativeQuery = true)
	void editaEstatusActivo(@Param("idResenaProducto") int idResenaProducto);
	
	@Query(value = "SELECT count(*) FROM bdgeneral.gen_aux_resena_producto where estatus = 0 and id_producto = :idProducto and id_usuario = :idUsuario", nativeQuery = true)
	 int resenaInactiva(@Param("idProducto") Long idProducto,@Param("idUsuario") Long idUsuario);

}
