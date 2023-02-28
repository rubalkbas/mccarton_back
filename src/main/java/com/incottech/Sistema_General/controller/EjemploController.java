/**
 * 
 */
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.MaestraCategoria;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.CategoriaRepository;



/**
 * @author Rubén Vazquez Acosta
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ejemplo")
public class EjemploController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EjemploController.class);
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@GetMapping(value = "/consultaCategoriaTodo",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultarCargo() {
		
		LOGGER.info("Entra a controller para consulta lista de Categorias..");
	
		Respuesta response = new Respuesta();
		
		response.setEstatus("ok");
		response.setMensaje("Consulta exitosa");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/consultaCategoriaHija")
	public ResponseEntity<Respuesta> fechaPermitidaComite(
			@RequestParam (name = "padre") Integer padre,@RequestParam  (name = "categoria") Integer categoria) {

		LOGGER.info("Entra a controller para saber si la fecha ingresada es permitidad !!!");
		
		Respuesta response = new Respuesta();
		List <MaestraCategoria> maestraCategoria;
		
		maestraCategoria = categoriaRepository.consultaCategoriaHija( padre );
		
		if (maestraCategoria.isEmpty()) {
			response.setMensaje("ULTIMO");
		}else {
			response.setLista(maestraCategoria);
			response.setMensaje("consulta exitosa");
		}
		
		



		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

}
