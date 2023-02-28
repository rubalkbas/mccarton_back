/**
 * 
 */
package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incottech.Sistema_General.domain.entity.AsuntosEntity;
import com.incottech.Sistema_General.domain.entity.AuxiliarImgProducto;

/**
 * @author rubal
 *
 */
public interface AsuntosRepository extends JpaRepository<AsuntosEntity, Long> {
	
	@Query(value = "select * from bdgeneral.gen_aux_img_producto where id_producto = :idProducto", nativeQuery = true)
	List <AsuntosEntity> consultaTodo( );


}
