package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.MaterialesEntity;


public interface IMaterialRepository extends JpaRepository<MaterialesEntity, Integer> {

}
