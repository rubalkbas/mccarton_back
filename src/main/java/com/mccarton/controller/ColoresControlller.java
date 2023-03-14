package com.mccarton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ColoresEntity;
import com.mccarton.service.IColoresService;

@RestController
@CrossOrigin("*")
@RequestMapping("/colores")
public class ColoresControlller {
	
	@Autowired
	private IColoresService ColoresService;

	
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<ColoresEntity>>> listarColores(){
		SingleResponse<List<ColoresEntity>> response = new SingleResponse<>();
		response = ColoresService.consultarColores();
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@PostMapping(path = "/nuevoColor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ColoresEntity>> crearColor (@RequestBody  ColoresEntity color){
		SingleResponse<ColoresEntity> response = new SingleResponse<>();
		response = ColoresService.crearColor(color);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@GetMapping(path = "/todosActivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<ColoresEntity>>> listarColoresActivos(){
		SingleResponse<List<ColoresEntity>> response = new SingleResponse<>();
		response = ColoresService.consultarColoresActivos();
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@PutMapping(path = "/actualizarEstatusColor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ColoresEntity>> actualizaEstatusColor(@RequestBody ColoresEntity color){
		SingleResponse<ColoresEntity> response = new SingleResponse<>();
		response = ColoresService.actualizarEstatusColor(color);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}

}
