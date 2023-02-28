package com.incottech.Sistema_General.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.incottech.Sistema_General.domain.entity.ListaDeseos;

/**
 * @author Mauricio Soto
 * @version 1.0
 * @since   2021-01-06
 */

public interface ListaDeseosRepository extends JpaRepository< ListaDeseos , Long > {
	@Modifying(clearAutomatically = true)
	@Transactional 
	@Query(value = "DELETE FROM bdgeneral.gen_lista_deseos WHERE id_producto = :idProducto AND id_usuario = :idUsuario", nativeQuery = true)
	void removerProducto(@Param("idProducto") Long idProducto, @Param("idUsuario") Long idUsuario);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "DELETE FROM bdgeneral.gen_lista_deseos  WHERE id_usuario = :idUsuario", nativeQuery = true)
	public void removerTodosLosProducto(@Param("idUsuario") Long idUsuario);

	@Modifying(clearAutomatically = true)
	@Transactional 
	@Query(value = "SELECT * FROM bdgeneral.gen_lista_deseos gld WHERE id_usuario = :idUsuario ", nativeQuery = true)
	public List<ListaDeseos> consultaWishList(@Param("idUsuario") String idUsuario);
}
