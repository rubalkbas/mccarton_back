package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.RolEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.repository.IRolRepository;
import com.mccarton.repository.IUsuarioRepository;


@Service
public class UsuarioService implements IUsuarioService {
	
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Transactional
	@Override
	public SingleResponse<List<UsuarioEntity>> consultarUsuarios() {
		List<UsuarioEntity> listaUsuarios = new ArrayList<>();
		
		try {
			listaUsuarios = usuarioRepository.findAll();
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		if(!listaUsuarios.isEmpty()) {
			SingleResponse<List<UsuarioEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de usuarios exitosamente");
			response.setResponse(listaUsuarios);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de usuarios en la BD");
	}


	@Transactional
	@Override
	public SingleResponse<UsuarioEntity> crearUsuario(UsuarioEntity usuario) {
		Optional<UsuarioEntity> usuarioO = Optional.empty();
		try {
			usuarioO = usuarioRepository.findBycorreoElectronicoIgnoreCase(usuario.getCorreoElectronico());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		if(usuarioO.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  correo electrónico " + usuario.getCorreoElectronico() +" ya existe en la BD");
		}
		usuario.setEstatus(1);
		Optional<RolEntity> rolOp = Optional.empty();
		try {
			rolOp = rolRepository.findById(usuario.getRol().getIdRol());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los roles en la BD");
		}
		if(!rolOp.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  rol con Id " + usuario.getRol().getIdRol() +" no existe en la BD");
		}

		usuario.setRol(rolOp.get());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		try {
			usuario = usuarioRepository.save(usuario);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado al usuario " + usuario.getNombreUsuario() +" exitosamente.");
		response.setResponse(usuario);
		return response;
	}

}
