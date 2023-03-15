package com.mccarton.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CategoriasEntity;
import com.mccarton.service.ICategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/categorias")
@Controller
public class CategoriasController {

	@Autowired
	private ICategoriaService categoriaService;
	
	
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<CategoriasEntity>>> consultarCategorias(){
		SingleResponse<List<CategoriasEntity>> response = new SingleResponse<List<CategoriasEntity>>();
		response = categoriaService.consultarCategorias();
		return new ResponseEntity<SingleResponse<List<CategoriasEntity>>>(response,HttpStatus.OK);
	}
	
	@PostMapping(path = "/guardarCategoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<CategoriasEntity>> guardarCategoria(@RequestBody CategoriasEntity categoria){
		SingleResponse<CategoriasEntity> response = new SingleResponse<CategoriasEntity>();
		response = categoriaService.guardarCategoria(categoria);
		return new ResponseEntity<SingleResponse<CategoriasEntity>>(response,HttpStatus.OK);
	}
	
	@PutMapping(value = "/actualizarCategoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<CategoriasEntity>> actualizarCategoria(@RequestBody CategoriasEntity categoria){
		SingleResponse<CategoriasEntity> response = new SingleResponse<CategoriasEntity>();
		response = categoriaService.actualizarCategoria(categoria);
		return new ResponseEntity<SingleResponse<CategoriasEntity>>(response,HttpStatus.OK);
	}
	
	@PutMapping(value = "/actualizarEstatusCategoria", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<CategoriasEntity>>actualizarEstatusCategoria(CategoriasEntity categoria ){
		SingleResponse<CategoriasEntity> response = new SingleResponse<CategoriasEntity>();
		response = categoriaService.actualizarEstatusCategoria(categoria);
		return new ResponseEntity<SingleResponse<CategoriasEntity>>(response,HttpStatus.OK);
	}
	
	@GetMapping(value = "/consultarCategoriasActivas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<CategoriasEntity>>> consultarCategoriasActivas(){
		SingleResponse<List<CategoriasEntity>> response = new SingleResponse <List<CategoriasEntity>>();
		response = categoriaService.consultarCategoriasActivas();
		return new ResponseEntity<SingleResponse<List<CategoriasEntity>>>(response,HttpStatus.OK);		
		
	}
	
	
	
}
