package com.mccarton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.OrdenDto;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.OrdenesEntity;
import com.mccarton.service.IOrdenesService;

@RestController
@RequestMapping("/ordenes")
public class OrdenesController {
	
	@Autowired
	private IOrdenesService ordenesService;
	
	@GetMapping(path = "/listarOrdenesPorPagina/page/{noPagina}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<Page<OrdenesEntity>>> listarOrdenesPorPagina(
			@PathVariable ("noPagina") Integer noPagina, @RequestParam ("campo") String campo,
			@RequestParam("direccion") String direccion,
			@RequestParam("buscar") String buscar){
		SingleResponse<Page<OrdenesEntity>> response = new SingleResponse<>();
		response = ordenesService.consultarPorPaginas(noPagina, campo, direccion, buscar);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@GetMapping(path = "/detalleOrden/{idOrden}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<OrdenDto>> detalleUsuario(@PathVariable ("idOrden") Integer idOrden){
		SingleResponse<OrdenDto> response = new SingleResponse<>();
		response = ordenesService.detalleOrden(idOrden);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
