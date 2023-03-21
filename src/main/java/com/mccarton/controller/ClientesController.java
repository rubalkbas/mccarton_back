/**
 * 
 */
package com.mccarton.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;
import com.mccarton.service.DireccionesServices;
import com.mccarton.service.IClienteService;

/**
 * @author ITTIVA
 * Clase encargada de manejar las rutas para manejasr los cleintes 
 */

@RestController
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<ClienteEntity>>> listaClientes(){
		
		SingleResponse<List<ClienteEntity>> response = new SingleResponse<>();
		response = clienteService.consultarClientes();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/consultarCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ClienteEntity>> consultarClientePorId(@PathVariable("id") Integer id){
		
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		response = clienteService.consultarClientePorId(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@PostMapping(path = "/nuevoCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ClienteEntity>> crearCliente(@RequestBody ClienteDireccion cliente){
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		response = clienteService.crearCliente(cliente);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
		
	}
	
	@DeleteMapping(path = "/eliminarCliente/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<SingleResponse<ClienteEntity>> eliminarCliente(@PathVariable Integer id) {
	    SingleResponse<ClienteEntity> response = new SingleResponse<>();
	    response = clienteService.eliminarCliente(id);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(path = "/actualizarCliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<ClienteEntity>> actualizarCliente(@RequestBody ClienteEntity cliente) {
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		response = clienteService.actualizarCliente(cliente);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	

}



