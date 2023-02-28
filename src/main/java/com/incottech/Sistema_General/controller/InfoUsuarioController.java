/**
 * 
 */
package com.incottech.Sistema_General.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.InfoUsuario;
import com.incottech.Sistema_General.domain.entity.MaestraCategoria;
import com.incottech.Sistema_General.domain.entity.domicilioUsuario;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.DomicilioUsuarioRepository;
import com.incottech.Sistema_General.repository.InfoUsuarioRepository;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/infoUsuario")
public class InfoUsuarioController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InfoUsuarioController.class);

	
	@Autowired
	private InfoUsuarioRepository infoUsuarioRepository;
	
	@Autowired
	private DomicilioUsuarioRepository domicilioUsuarioRepository;
	
	

	@PostMapping(value = "/consultaIdUsuario")
	public ResponseEntity<Respuesta> consultaIdUsuario(
			@RequestParam (name = "idUsuario") Long idUsuario) {

		LOGGER.info("Entra a controller para buscar info personal del usuario por idUsuario !!!");
		
		Respuesta response = new Respuesta();
		InfoUsuario usuairo = new InfoUsuario();
		
		usuairo = infoUsuarioRepository.consultaIdUsuario( idUsuario );
		
		if (usuairo == null) {
			response.setMensaje("null");
		}else {
			response.setDto(usuairo);
			response.setMensaje("consulta exitosa");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/guardaInfoUsuario")
	public ResponseEntity<Respuesta> guardaInfoUsuario(@Valid @RequestBody InfoUsuario infoUsuario )  {

		LOGGER.info("Entra a controller para guardar info personal del usuario!!!");
		
		
		

		infoUsuarioRepository.save( infoUsuario );
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/guardaDomicilioUsuario")
	public ResponseEntity<Respuesta> guardaDomicilioUsuario(@Valid @RequestBody domicilioUsuario domUsu )  {

		LOGGER.info("Entra a controller para guardar el domicilio de envio del usuario!!!");
		
		domicilioUsuarioRepository.save( domUsu );
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	@PostMapping(value = "/consultaDomicilioUsuario")
	public ResponseEntity<Respuesta> consultaDomicilioUsuario(
			@RequestParam (name = "idUsuario") Long idUsuario) {

		LOGGER.info("Entra a controller para buscar info de los domicilios del usuario !!!");
		
		Respuesta response = new Respuesta();
		
		List<domicilioUsuario> listaDomicilios;
		
		
		listaDomicilios = domicilioUsuarioRepository.consultaDomicilioUsuario( idUsuario );
		
		if (listaDomicilios == null) {
			response.setMensaje("No hay domicilios");
		}else {
			response.setLista(listaDomicilios);

			response.setMensaje("consulta exitosa");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/consultaDomPredeUsuario")
	public ResponseEntity<Respuesta> consultaDomPredeUsuario(
			@RequestParam (name = "idUsuario") Long idUsuario) {

		LOGGER.info("Entra a controller para buscar info de los domicilios del usuario !!!");
		
		Respuesta response = new Respuesta();
		
		List<domicilioUsuario> listaDomicilios;
		
		
		listaDomicilios = domicilioUsuarioRepository.consultaDomPredeUsuario( idUsuario );
		
		if (listaDomicilios == null) {
			response.setMensaje("No hay domicilios");
		}else {
			response.setLista(listaDomicilios);

			response.setMensaje("consulta exitosa");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/guardaDomPredeUsuario")
	public ResponseEntity<Respuesta> guardaDomPredeUsuario(
			@RequestParam (name = "idUsuario") Long idUsuario, @RequestParam (name = "idDomi") Long idDomi) {

		LOGGER.info("Entra a controller para buscar info de los domicilios del usuario !!!");
		
		domicilioUsuarioRepository.actualizaCeroDomiPrede( idUsuario );
		
		domicilioUsuarioRepository.guardaDomPredeUsuario( idUsuario,idDomi );
		
			
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@PostMapping(value = "/consultaDomOrden")
	public ResponseEntity<Respuesta> consultaDomOrden(
			@RequestParam (name = "idDomicilioUsuario") Long idDomicilioUsuario) {

		LOGGER.info("Entra a controller para buscar info de los domicilios del usuario !!!");
		
		Respuesta response = new Respuesta();
		
		List<domicilioUsuario> domicilio;
		
		
		domicilio = domicilioUsuarioRepository.consultaDomOrden(idDomicilioUsuario);
		
		if (domicilio == null) {
			response.setMensaje("No hay domicilio");
		}else {
			response.setLista(domicilio);

			response.setMensaje("consulta exitosa");
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
