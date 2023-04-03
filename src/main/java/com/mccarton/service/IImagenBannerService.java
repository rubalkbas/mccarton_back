package com.mccarton.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ImagenBannerEntity;

public interface IImagenBannerService {

	SingleResponse<List<ImagenBannerEntity>> consultarTodosImagenBanner();
	SingleResponse<ImagenBannerEntity> guardarImagenBanner(ImagenBannerEntity imagenBanner, MultipartFile imagen) throws IOException;
	SingleResponse<ImagenBannerEntity>actualizarImagenBanner(ImagenBannerEntity imagenBannerNueva,MultipartFile imagen) throws IOException;
	SingleResponse<ImagenBannerEntity>eliminarImagenBanner(Integer idImagenBanner);
	SingleResponse<ImagenBannerEntity> actualizarEstatusImagenBanner(Integer idImagenBanner,Integer estatus);
	SingleResponse<List<ImagenBannerEntity>> consultarTodosActivosImagenBanner();
}
