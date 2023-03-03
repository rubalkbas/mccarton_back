package com.mccarton.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.UsuarioEntity;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{

}
