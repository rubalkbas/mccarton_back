package com.mccarton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.CarroComprasRequest;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CarroComprasEntity;
import com.mccarton.service.ICarritoCompraService;

@RestController
@RequestMapping("/carroCompras")
public class CarritoComprasController {
	
	@Autowired
	private ICarritoCompraService carritoCompraService;
	
	@PostMapping(path = "/agregarProducto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<CarroComprasEntity>>> agregarProducto(@RequestBody CarroComprasRequest request){
		SingleResponse<List<CarroComprasEntity>> response = new SingleResponse<>();
		response = carritoCompraService.agregarProducto(request);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}

}
