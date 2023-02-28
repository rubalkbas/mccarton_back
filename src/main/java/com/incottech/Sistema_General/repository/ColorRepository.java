/**
 * 
 */
package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.ColorProducto;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Repository
public interface ColorRepository extends JpaRepository <ColorProducto, Long>{
	
	@Query(value = "select * from bdgeneral.gen_aux_color_producto where status = 1 and id_producto = :idProducto", nativeQuery = true)
	List <ColorProducto> consultaColorProductoPorID(@Param("idProducto") Long idProducto);

	
}
