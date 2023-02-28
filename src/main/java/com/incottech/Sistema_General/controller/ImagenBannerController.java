package com.incottech.Sistema_General.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.AuxImagenBanner;
import com.incottech.Sistema_General.domain.entity.ImgBannerCategoria;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.BannerCategoriaRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ImgBanner")
public class ImagenBannerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImagenBannerController.class);
	
	
	@Autowired
	private BannerCategoriaRepository imagenBannerRepository;

	
	@GetMapping(value = "/consultaProductosAleatoriosSlides",  produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Respuesta> consultaProductosAleatorios() {
		
		LOGGER.info("Entra a controller para consulta lista de Imagen Banners..");
	
		Respuesta response = new Respuesta();
		List <ImgBannerCategoria> imagenBanner;
		
		imagenBanner = imagenBannerRepository.consultaProductosAleatorios();

		response.setMensaje("Consulta exitosa");
		response.setLista(imagenBanner);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
