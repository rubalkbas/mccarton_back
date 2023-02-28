/**
 * 
 */
package com.incottech.Sistema_General.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.CatCodigoPostal;
import com.incottech.Sistema_General.domain.entity.MaestraCategoria;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.CatalogosRepository;

/**
 * @author Rubén Vazquez Acosta
 *
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/catalogos")
public class CatalogoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogoController.class);
	
	@Autowired
	private CatalogosRepository catalogosRepository;
	
	@PostMapping(value = "/consultaCategoriaHija")
	public ResponseEntity<Respuesta> fechaPermitidaComite(
			@RequestParam (name = "cp") String cp) {

		LOGGER.info("Entra a controller para saber la entidad, colonia y demas por Codigo Postal !!!");
		
		Respuesta response = new Respuesta();
		List <CatCodigoPostal> maestraCategoria;
		
		maestraCategoria = catalogosRepository.consultaPorCP( cp );
		
		if (maestraCategoria.isEmpty()) {
			response.setMensaje("ULTIMO");
		}else {
			response.setLista(maestraCategoria);
			response.setMensaje("consulta exitosa");
		}
		
		



		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
