package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.mccarton.model.entity.ReseniaEntity;

public interface IReseniaRepository extends JpaRepository<ReseniaEntity, Integer>{

	@Query("SELECT d FROM ReseniaEntity d WHERE ID_CLIENTE =:idCliente AND ID_PRODUCTO =:idProducto ")
	Optional<ReseniaEntity> findByClienteProducto(@Param("idCliente") Integer idCliente, @Param("idProducto") Integer idProducto);
	
//	@Query("SELECT d FROM ReseniaEntity d WHERE ID_CLIENTE =:idCliente")
//	Optional<List<ReseniaEntity>> findByReseniaCliente(@Param("idCliente") Integer idCliente);
	
}
