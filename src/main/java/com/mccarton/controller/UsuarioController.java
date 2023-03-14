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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(path = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<UsuarioEntity>>> listarUsuarios(){
		SingleResponse<List<UsuarioEntity>> response = new SingleResponse<>();
		response = usuarioService.consultarUsuarios();
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@PostMapping(path = "/nuevoUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<UsuarioEntity>> crearRol(@RequestBody  UsuarioEntity usuario){
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response = usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
	
	@PutMapping(path = "/actualizarUsuario", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<UsuarioEntity>> actualizarUsuario(@RequestBody UsuarioEntity usuario){
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response = usuarioService.actualizarUsuario(usuario);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/eliminarUsuario/{idUsuario}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<UsuarioEntity>> eliminarUsuario(@PathVariable ("idUsuario") Integer idUsuario){
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response = usuarioService.eliminarUsuario(idUsuario);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/listarUsuariosActivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SingleResponse<List<UsuarioEntity>>> listarUsuariosActivos(){
		SingleResponse<List<UsuarioEntity>> response = new SingleResponse<>();
		response = usuarioService.consultarUsuariosActivos();
		return new ResponseEntity<>(response, HttpStatus.OK); 	 //Se crea respuesta Ok
	}
}
