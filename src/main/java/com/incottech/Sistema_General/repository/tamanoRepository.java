/**
 * 
 */
package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.tamanoProducto;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Repository
public interface tamanoRepository  extends JpaRepository<tamanoProducto, Long>  {
	
	@Query(value = "select * from bdgeneral.gen_aux_tamano_producto where status = 1 and id_producto = :idProducto", nativeQuery = true)
	List <tamanoProducto> consultaTamanoProductoPorID(@Param("idProducto") Long idProducto);
	
}
