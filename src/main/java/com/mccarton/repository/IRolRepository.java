package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.RolEntity;

public interface IRolRepository extends JpaRepository<RolEntity, Integer>{

}
