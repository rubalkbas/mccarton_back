/**
 * 
 */
package com.incottech.Sistema_General.controller;

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

import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.AsuntosRepository;

/**
 * @author rubal
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/asuntos")
public class AsuntosController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EjemploController.class);

	@Autowired
	AsuntosRepository asuntosRepository;
	
	
	@GetMapping(value = "/consultaAsuntosTodo",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultarCargo() {
		
		LOGGER.info("Entra a controller para consulta lista de Categorias..");
	
		Respuesta response = new Respuesta();
		
		response.setLista(asuntosRepository.findAll());
		response.setEstatus("ok");
		response.setMensaje("Consulta exitosa");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
