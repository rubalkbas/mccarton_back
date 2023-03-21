package com.mccarton.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mccarton.model.entity.ClienteEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {
	
	Optional<ClienteEntity> findBycorreoElectronicoIgnoreCase(String id);
	
}
