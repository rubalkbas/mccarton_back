package com.mccarton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.ListaDeseosEntity;
import com.mccarton.model.entity.ProductosEntity;

public interface IListaDeseosRepository extends JpaRepository<ListaDeseosEntity, Integer>{

	List<ListaDeseosEntity> findByCliente(ClienteEntity cliente);
	
}
