package com.incottech.Sistema_General.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.MaestraCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository< MaestraCategoria , Long > {

	@Query(value = "SELECT * FROM bdgeneral.gen_mae_categoria WHERE estatus = 1 AND nivel_padre = :padre", nativeQuery = true)
	List <MaestraCategoria> consultaCategoriaHija(@Param("padre") Integer padre );
	
	@Query(value = "SELECT * FROM bdgeneral.gen_mae_categoria WHERE estatus = 1 ", nativeQuery = true)
	List <MaestraCategoria> consultaCategoriaTodo();
	
	@Query(value = "SELECT DISTINCT gmc.desc_categoria  FROM bdgeneral.gen_mae_categoria AS gmc WHERE estatus = 1 ORDER BY desc_categoria ASC", nativeQuery = true)
	List <MaestraCategoria> consultaCategoriaSinDuplicado();
	
	@Query(value = "SELECT * FROM bdgeneral.gen_mae_categoria WHERE estatus = 1 AND nivel_padre = 0", nativeQuery = true)
	List <MaestraCategoria> consultaCategoria0();
	
}
