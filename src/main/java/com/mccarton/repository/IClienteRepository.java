package com.mccarton.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ClienteEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	Optional<ClienteEntity> findBycorreoElectronicoIgnoreCase(String id);

}
