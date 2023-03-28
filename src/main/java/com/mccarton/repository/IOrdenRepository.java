package com.mccarton.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mccarton.model.entity.OrdenesEntity;

public interface IOrdenRepository extends JpaRepository<OrdenesEntity, Integer> {
	@Query("SELECT o FROM OrdenesEntity o WHERE o.cliente.nombre LIKE %?1% OR "
			+ "o.cliente.apellidoPaterno LIKE %?1% OR o.cliente.apellidoMaterno LIKE %?1% OR "
			+ "o.cliente.telefono LIKE %?1% OR o.calle LIKE %?1% OR "
			+ "o.numeroExterior LIKE %?1% OR o.codigoPostal LIKE %?1% OR "
			+ "o.colonia LIKE %?1% OR o.ciudad LIKE %?1% OR "
			+ "o.metodoPago LIKE %?1% OR o.estatusOrden LIKE %?1%")
	//		+ "JOIN ClienteEntity c WHERE o.id_cliente = c.id_cliente")
	Page<OrdenesEntity> findAll(String search, Pageable pageable);

}
