package com.mccarton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;
import com.mccarton.service.DireccionesServices;

@RestController
@RequestMapping("/direcciones")
public class DireccionesController {

	@Autowired
	DireccionesServices direccionesService;
	
	@GetMapping(path = "/consultarDireccionPorCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<DireccionEntity>>> consultarDireccionPorId(@PathVariable("id") Integer id){
		
		SingleResponse<List<DireccionEntity>> response = new SingleResponse<>();
		response = direccionesService.consultarDireccionesPorCliente(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarDireccionPredeterminada/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<DireccionEntity>> consultarDireccionPredeterminada(@PathVariable("id") Integer id){
		
		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response = direccionesService.consultarDireccionDefecto(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/eliminarDireccion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<DireccionEntity>> eliminarCliente(@PathVariable Integer id) {
	    SingleResponse<DireccionEntity> response = new SingleResponse<>();
	    response = direccionesService.eliminarDireccion(id);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(path = "/nuevaDireccion/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<DireccionEntity>> crearCliente(@RequestBody DireccionEntity direccion, ClienteEntity idCliente){
		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response = direccionesService.crearNuevaDireccion(direccion, idCliente);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
		
	}
	
	@PostMapping(path = "/actualizarDireccion/{idCliente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<DireccionEntity>> actualizarDireccion(@RequestBody DireccionEntity direccion, ClienteEntity idCliente){
		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response = direccionesService.actualizarDireccion(direccion, idCliente);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
		
	}
	
}
