package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mccarton.model.entity.CarroComprasEntity;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.ProductosEntity;

public interface ICarroComprasRepository extends JpaRepository<CarroComprasEntity, Integer>{
	Optional<CarroComprasEntity> findByClienteAndProducto(ClienteEntity cliente, ProductosEntity producto);
	List<CarroComprasEntity> findByCliente(ClienteEntity cliente);
	@Modifying
	@Query("DELETE CarroComprasEntity c WHERE c.cliente.idCliente = ?1")
	void deleteByCliente(Integer idCliente);

}
