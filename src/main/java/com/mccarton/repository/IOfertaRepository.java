package com.mccarton.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.OfertaEntity;

public interface IOfertaRepository extends JpaRepository<OfertaEntity, Integer> {

	List<OfertaEntity> findByEstatus(Integer estatus);
    List<OfertaEntity> findByFechaFinBeforeAndEstatusNot(LocalDateTime fechaFin, Integer estatus);
	
}
