package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	public static final Integer ESTATUS_ACTIVO = 1;
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
			usuarioO = usuarioRepository.findByCorreoElectronicoIgnoreCase(usuario.getCorreoElectronico());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		if(usuarioO.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  correo electrónico " + usuario.getCorreoElectronico() +" ya existe en la BD");
		}
		usuario.setCorreoElectronico(usuario.getCorreoElectronico().toLowerCase());
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


	@Transactional
	@Override
	public SingleResponse<UsuarioEntity> actualizarUsuario(UsuarioEntity usuario) {
		Optional<UsuarioEntity> oUsuarioDb = Optional.empty();
		try {
			oUsuarioDb = usuarioRepository.findById(usuario.getIdUsuario());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		
		if(!oUsuarioDb.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  usuario con Id " + usuario.getIdUsuario() +" no existe en la BD");
		}
		UsuarioEntity usuarioDb = oUsuarioDb.get();
		if(usuario.getNombreUsuario()!= null) {
			usuarioDb.setNombreUsuario(usuario.getNombreUsuario());
		}
		if(usuario.getApellidoPaterno()!= null) {
			usuarioDb.setApellidoPaterno(usuario.getApellidoPaterno());
		}
		if(usuario.getApellidoMaterno()!= null) {
			usuarioDb.setApellidoMaterno(usuario.getApellidoMaterno());
		}
		if(usuario.getCorreoElectronico()!= null) {
			Optional<UsuarioEntity> usuarioO = Optional.empty();
			try {
				usuarioO = usuarioRepository.findByCorreoElectronicoIgnoreCase(usuario.getCorreoElectronico());
			} catch (DataAccessException ex) {
				log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
						ex.getStackTrace());
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
			}
			if(usuarioO.isPresent()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, "El  correo electrónico " + usuario.getCorreoElectronico() +" ya existe en la BD");
			}
			usuarioDb.setCorreoElectronico(usuario.getCorreoElectronico().toLowerCase());
		}
		if(usuario.getEstatus()!= null) {
			usuarioDb.setEstatus(usuario.getEstatus());
		}
		if(usuario.getRol().getIdRol()!= null) {
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
			usuarioDb.setRol(rolOp.get());
		}
		if(usuario.getPassword()!= null) {
			usuarioDb.setPassword(passwordEncoder.encode(usuario.getPassword()));
		}
		if(usuario.getFoto()!= null) {
			usuarioDb.setFoto(usuario.getFoto());
		}
		try {
			usuarioDb = usuarioRepository.save(usuarioDb);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha actualizado al usuario " + usuarioDb.getNombreUsuario() +" exitosamente.");
		response.setResponse(usuarioDb);
		return response;
	}


	@Transactional
	@Override
	public SingleResponse<UsuarioEntity> eliminarUsuario(Integer idUsuario) {
		Optional<UsuarioEntity> oUsuarioDb = Optional.empty();
		try {
			oUsuarioDb = usuarioRepository.findById(idUsuario);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		
		if(!oUsuarioDb.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  usuario con Id " + idUsuario +" no existe en la BD");
		}
		
		try {
			usuarioRepository.deleteById(idUsuario);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar el usuario en la BD");
		}
		SingleResponse<UsuarioEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha eliminado al usuario exitosamente.");
		
		return response;
	}


	@Transactional
	@Override
	public SingleResponse<List<UsuarioEntity>> consultarUsuariosActivos() {
List<UsuarioEntity> listaUsuarios = new ArrayList<>();
		
		try {
			listaUsuarios = usuarioRepository.findByEstatusOrderByApellidoPaternoAscApellidoMaternoAscNombreUsuarioAsc(ESTATUS_ACTIVO);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		if(!listaUsuarios.isEmpty()) {
			SingleResponse<List<UsuarioEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de usuarios activos exitosamente");
			response.setResponse(listaUsuarios);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de usuarios activos en la BD");
	}


	@Override
	public SingleResponse<Page<UsuarioEntity>> consultarPorPaginas(int noPagina, String campo, String direccion, String buscar) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(noPagina-1, pageSize,
				direccion.equalsIgnoreCase("asc") ? Sort.by(campo).ascending()
						: Sort.by(campo).descending());
		 
		Page<UsuarioEntity> usuarioPage = Page.empty();
		
		try {
			if (buscar != null) {
				usuarioPage = usuarioRepository.findAll(buscar, pageable);
			}else {
				usuarioPage = usuarioRepository.findAll(pageable);
			}
			
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		
		if (!usuarioPage.isEmpty()) {
			SingleResponse<Page<UsuarioEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de usuarios activos exitosamente");
			response.setResponse(usuarioPage);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros en la página " + noPagina);
	}

}
