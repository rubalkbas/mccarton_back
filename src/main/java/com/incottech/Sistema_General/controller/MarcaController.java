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

import com.incottech.Sistema_General.domain.entity.CatalogoMarca;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.CatalogoMarcaRepository;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/marca")
public class MarcaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MarcaController.class);
	
	@Autowired
	private CatalogoMarcaRepository catalogoMarcaRepository;

	
	///////////////////////////  Metodos para el catalogo de marcas  //////////////////////////////////////
	
	@GetMapping(value = "/consultaCatalogoMarcaTodo",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaCatalogoMarcaTodo() {
		
		LOGGER.info("Entra a controller para consulta lista de todas las marcas");
	
		Respuesta response = new Respuesta();
		List <CatalogoMarca> catalogoMarca;
		
		catalogoMarca = catalogoMarcaRepository.consultaCatalogoMarcaTodo();

		response.setMensaje("Consulta exitosa");
		response.setLista(catalogoMarca);
		
		LOGGER.info("Sale de controller para consulta lista de todas las marcas");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	


}
