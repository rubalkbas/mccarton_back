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

import com.incottech.Sistema_General.domain.entity.OrdenCompra;

/**
 * @author Rubalkbas
 *
 */
@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long>{
	
	@Query(value = "select * from bdgeneral.gen_mae_orden_compra where id_orden_compra = :idOrdenCompra ", nativeQuery = true)
	OrdenCompra consultaOrdenCompraID(@Param("idOrdenCompra") Long idOrdenCompra);
	
	@Query(value = "SELECT * FROM bdgeneral.gen_mae_orden_compra gmoc WHERE gmoc.id_usuario = :idUsuario AND gmoc.estatus_orden_compra = 'COMPLETADO'", nativeQuery = true)
	List<OrdenCompra> consultaOrdenesCompletas(@Param("idUsuario") String idUsuario);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_mae_orden_compra set id_comicilio_usuario = :idDomicilio WHERE id_orden_compra = :idOrdenCompra", nativeQuery = true)
	void agregarDomicilio(@Param("idDomicilio") long idD, @Param("idOrdenCompra") long idOC);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_mae_orden_compra set codigo_rastreo = :codRastreo WHERE id_orden_compra = :idOrdenCompra", nativeQuery = true)
	void agregarCodigoRastro(@Param("idOrdenCompra") long idOrdenCompra, @Param("codRastreo") String codRastreo);

	

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "delete from bdgeneral.gen_mae_orden_compra where id_usuario = :idUsuario and estatus_orden_compra = 'PENDIENTE PAGO'", nativeQuery = true)
	void eliminaOrdenCompraIdUsuaerio(@Param("idUsuario") Long idUsuario);
}
