package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ColoresEntity;

public interface IColorRepository extends JpaRepository<ColoresEntity, Integer> {

}
