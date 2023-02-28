package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.AuxiliarImgProducto;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface AuxiliarImgProductoRepository extends JpaRepository< AuxiliarImgProducto , Long >{
	
	@Query(value = "select * from bdgeneral.gen_aux_img_producto where id_producto = :idProducto", nativeQuery = true)
	List <AuxiliarImgProducto> consultaImagenesProductoPorID(@Param("idProducto") Long idProducto);

}
