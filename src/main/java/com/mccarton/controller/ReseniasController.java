package com.mccarton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.SingleResponse;

import com.mccarton.model.entity.ReseniaEntity;
import com.mccarton.service.IReseniaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/resenias")
public class ReseniasController {
	
	@Autowired
	IReseniaService reseniaService;
	
	@PostMapping(path = "/crearResenia", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ReseniaEntity>> crearResenia(@RequestBody ReseniaEntity resenia){
		SingleResponse<ReseniaEntity> response = new SingleResponse<ReseniaEntity>();
		response = reseniaService.crearResenia(resenia);
		return new ResponseEntity<SingleResponse<ReseniaEntity>>(response,HttpStatus.OK);
	}
	
	@GetMapping(path = "/lsitarResenias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<ReseniaEntity>>> listarResenias(){
		SingleResponse<List<ReseniaEntity>> response = new SingleResponse<List<ReseniaEntity>>();
		response = reseniaService.consultarResenia();
		return new ResponseEntity<SingleResponse<List<ReseniaEntity>>>(response,HttpStatus.OK);
	}
	
	@GetMapping(path = "/listarReseniasClienteProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ReseniaEntity>> listarReseniasClienteProdudcto(@RequestParam Integer idCliente, @RequestParam Integer idProducto ){
		SingleResponse<ReseniaEntity> response = reseniaService.consultarReseniaClienteProducto(idCliente, idProducto);
		return new ResponseEntity<SingleResponse<ReseniaEntity>>(response,HttpStatus.OK);
	}
	
	@GetMapping(path = "/listarReseniasCliente", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<ReseniaEntity>>> consultarReseniasCliente(@RequestParam Integer idCliente){
		SingleResponse<List<ReseniaEntity>> response = reseniaService.consultarReseniaCliente(idCliente);
		return new ResponseEntity<SingleResponse<List<ReseniaEntity>>>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/lsitarReseniaId/{idResenia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ReseniaEntity>> listarReseniaId(@PathVariable ("idResenia") Integer idResenia){
		SingleResponse<ReseniaEntity> response = new SingleResponse<ReseniaEntity>();
		response = reseniaService.consultarReseniaDetalle(idResenia);
		return new ResponseEntity<SingleResponse<ReseniaEntity>>(response,HttpStatus.OK);
	}
	
	@PutMapping(path = "/actualizarResenia", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ReseniaEntity>> actualizarResenia(@RequestBody ReseniaEntity resenia){
		SingleResponse<ReseniaEntity> response = new SingleResponse<ReseniaEntity>();
		response = reseniaService.actualizarResenia(resenia);
		return new ResponseEntity<SingleResponse<ReseniaEntity>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/eliminarResenia/{idResenia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ReseniaEntity>> eliminarResenia(@PathVariable("idResenia") Integer idResenia){
		SingleResponse<ReseniaEntity> response = new SingleResponse<ReseniaEntity>();
		response = reseniaService.borrarResenia(idResenia);
		return new ResponseEntity<SingleResponse<ReseniaEntity>>(response,HttpStatus.OK);
	}


}
