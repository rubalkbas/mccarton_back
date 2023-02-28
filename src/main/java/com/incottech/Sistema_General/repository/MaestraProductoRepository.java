package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.MaestraProducto;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface MaestraProductoRepository extends JpaRepository< MaestraProducto , Long > {
	
	@Query(value = "select * from bdgeneral.gen_mae_producto", nativeQuery = true)
	List <MaestraProducto> consultaTodosProductos();
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where status = 1 ORDER BY RAND() LIMIT 25", nativeQuery = true)
	List <MaestraProducto> consultaProductosDestacados();
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where status = 1 and discount != 0 ORDER BY RAND() LIMIT 25", nativeQuery = true)
	List <MaestraProducto> consultaProductosDescuento();
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where status = 1 ORDER BY RAND() LIMIT 25", nativeQuery = true)
	List <MaestraProducto> consultaProductosValorados();
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where status = 1  ORDER BY RAND() LIMIT 25", nativeQuery = true)
	List <MaestraProducto> consultaProductosAgregadosReciente();
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where status = 1 and id_producto not in (:idProducto) and category_id like CONCAT(:idCategoria,'%') ORDER BY RAND() LIMIT 25", nativeQuery = true)
	List <MaestraProducto> consultaProductosRelacionados(@Param("idCategoria") Long idCategoria, @Param("idProducto") Long idProducto);
	
	@Query(value = "select * from bdgeneral.gen_mae_producto where id_producto = :idProducto", nativeQuery = true)
	MaestraProducto consultaProductoPorID(@Param("idProducto") Long idProducto);
	
	@Query(value = "SELECT\r\n" + 
			"	a.id_producto,\r\n" + 
			"	a.availibility_count ,\r\n" + 
			"	a.cart_count ,\r\n" + 
			"	a.category_id ,\r\n" + 
			"	a.description ,\r\n" + 
			"	a.discount ,\r\n" + 
			"	a.fec_alta ,\r\n" + 
			"	a.name ,\r\n" + 
			"	a.new_price ,\r\n" + 
			"	a.old_price ,\r\n" + 
			"	a.ratings_count ,\r\n" + 
			"	a.ratings_value ,\r\n" + 
			"	a.status ,\r\n" + 
			"	a.weight ,\r\n" + 
			"	a.condicion, \r\n" + 
			"	a.id_Cat_Marca \r\n" + 
			"FROM\r\n" + 
			"	bdgeneral.gen_mae_producto a\r\n" + 
			"RIGHT JOIN bdgeneral.gen_mae_categoria b ON\r\n" + 
			"	a.category_id = b.id_categoria\r\n" + 
			"WHERE\r\n" + 
			"	b.estatus  = 1 AND\r\n" + 
			"	a.status = 1 AND\r\n" + 
			"	a.category_id like CONCAT(:idNivelCategoria,'%') order by a.category_id asc", nativeQuery = true)
	List <MaestraProducto> listaProductosCategoria(@Param("idNivelCategoria") Integer idNivelCategoria);

}
