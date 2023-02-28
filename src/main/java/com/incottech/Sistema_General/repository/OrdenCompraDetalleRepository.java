package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.OrdenCompraDetalle;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface OrdenCompraDetalleRepository extends JpaRepository<OrdenCompraDetalle,Long> {
	
	@Query(value = "select * from bdgeneral.gen_aux_orden_compra_detalle where id_orden_compra = :idOrdenCompra ", nativeQuery = true)
	List <OrdenCompraDetalle> consultaOrdenCompraDetalleID(@Param("idOrdenCompra") Long idOrdenCompra);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "update bdgeneral.gen_aux_orden_compra_detalle set estatus_detalle_pedido = 'RECOLECTADO' "
			+ "where id_orden_compra = :idOrdenCompra and id_producto = :idProduto", nativeQuery = true)
	void productoOrdenRecolectado(@Param("idOrdenCompra") Long idOrdenCompra, @Param("idProduto") Long idProduto);
}
