package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ClienteEntity;

public interface IClienteEntity extends JpaRepository<ClienteEntity, Integer>{

}
