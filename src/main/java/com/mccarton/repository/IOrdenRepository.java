package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.OrdenesEntity;

public interface IOrdenRepository extends JpaRepository<OrdenesEntity, Integer> {

}
