package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.CatalogoMarca;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Repository
public interface CatalogoMarcaRepository extends JpaRepository< CatalogoMarca , Long > {
	
	@Query(value = "SELECT * FROM bdgeneral.gen_cat_marca WHERE estatus = 1 ", nativeQuery = true)
	List <CatalogoMarca> consultaCatalogoMarcaTodo();

}
