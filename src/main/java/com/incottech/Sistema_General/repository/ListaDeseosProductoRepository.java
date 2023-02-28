package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.ListaDeseoProducto;


/**
 * @author Mauricio Soto
 * @version 1.0
 * @since   2021-01-06
 */
public interface ListaDeseosProductoRepository extends JpaRepository< ListaDeseoProducto , Long > {
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "SELECT * FROM bdgeneral.view_lista_deseo_producto vldp  WHERE id_usuario = :idUsuario", nativeQuery = true)
	public List<ListaDeseoProducto> traerProductUsu(@Param("idUsuario") Long idUsuario);

}
