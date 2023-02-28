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

import com.incottech.Sistema_General.domain.entity.CarroCompras;
import com.incottech.Sistema_General.domain.entity.CatCodigoPostal;
import com.incottech.Sistema_General.domain.entity.OrdenCompraDetalle;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.CarroComprasRepository;

/**
 * @author Rubalkbas
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carrito")
public class CarroComprasController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarroComprasController.class);
	
	

	@Autowired
	CarroComprasRepository CarroComprasRepository;
	
	
	
	@PostMapping(value = "/guardaCarrito")
	public ResponseEntity<Respuesta> guardaCarrito(@Valid @RequestBody CarroCompras carroCompras )  {

		Respuesta response = new Respuesta();
		
		LOGGER.info("Entra a controller para guardar el carro de Compras!!!");
		
		CarroComprasRepository.save(carroCompras);
		response.setMensaje("Carro de compras Registrado Correctamente!");
		return ResponseEntity.ok(response);
		
	}
	
	
	@PostMapping(value = "/borraCarrito/")
	public ResponseEntity<Respuesta> borraCarrito(@RequestParam (name = "idUsuario") String idUsuario )  {

		Respuesta response = new Respuesta();
		
		LOGGER.info("Entra a controller para borrar el carro de Compras!!!");
		
		CarroComprasRepository.borraCarrito(idUsuario);
		response.setMensaje("Carro de compras Borrado Correctamente!");
		return ResponseEntity.ok(response);
		
	}

	@PostMapping(value = "/consultaCarroCompras")
	public ResponseEntity<Respuesta> consultaCarroCompras(
			@RequestParam (name = "idUsuario") String idUsuario) {

		LOGGER.info("Entra a controller para consultar carro de compras por id de usuario !!!");
		
		Respuesta response = new Respuesta();
		List <CarroCompras> carroCompras;
		
		carroCompras = CarroComprasRepository.consultaCarroCompras( idUsuario );
		
		if (carroCompras.isEmpty()) {
			response.setMensaje("VACIO");
		}else {
			response.setLista(carroCompras);
			response.setMensaje("consulta exitosa");
		}
		
		



		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
}
