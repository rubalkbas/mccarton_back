package com.mccarton.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mccarton.model.entity.ImagenBannerEntity;

public interface IImagenBannerRepository extends JpaRepository<ImagenBannerEntity, Integer> {
	
	List<ImagenBannerEntity> findByEstatus(Integer estatus);

}
