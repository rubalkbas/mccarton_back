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
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public SingleResponse<List<ClienteEntity>> consultarCliente() {
		List<ClienteEntity> listaCliente = new ArrayList<>();

		try {
			listaCliente = clienteRepository.findAll();
		} catch (Exception ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
		}
		if (!listaCliente.isEmpty()) {
			SingleResponse<List<ClienteEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de clientes exitosamente");
			response.setResponse(listaCliente);
			return response;
		}

		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de usuarios en la BD");
	}

	@Transactional
	@Override
	public SingleResponse<ClienteEntity> crearCliente(ClienteEntity cliente) {
		Optional<ClienteEntity> clienteO = Optional.empty();
		try {
			clienteO = clienteRepository.findBycorreoElectronicoIgnoreCase(cliente.getCorreoElectronico());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}

		if (clienteO.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST,
					"El  correo electrónico " + cliente.getCorreoElectronico() + " ya existe en la BD");
		}

		cliente.setApellidoMaterno(cliente.getApellidoMaterno());
		cliente.setApellidoPaterno(cliente.getApellidoPaterno());
		cliente.setCorreoElectronico(cliente.getCorreoElectronico());
		cliente.setEstatus(cliente.getEstatus());
		cliente.setNombre(cliente.getNombre());
		cliente.setPassword(cliente.getPassword());
		cliente.setTelefono(cliente.getTelefono());

		try {
			cliente = clienteRepository.save(cliente);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los clientes2 en la BD");
		}
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado al usuario " + cliente.getNombre() + " exitosamente.");
		response.setResponse(cliente);
		return response;
	}

	@Transactional
	@Override
	public SingleResponse<ClienteEntity> eliminarCliente(Integer id) {
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		Optional<ClienteEntity> clienteO = Optional.empty();

		try {
			clienteO = clienteRepository.findById(id);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
		}

		if (clienteO.isPresent()) {
			clienteRepository.delete(clienteO.get());
			response.setOk(true);
			response.setMensaje("Cliente eliminado exitosamente");
			response.setResponse(clienteO.get());
			return response;
		} else {
			log.error("No se pudo encontrar al cliente con el ID proporcionado");
			return null;
		}

	}

	@Transactional
	@Override
	public SingleResponse<ClienteEntity> actualizarCliente(ClienteEntity cliente) {
		Optional<ClienteEntity> clienteOBd = Optional.empty();

		try {
			clienteOBd = clienteRepository.findById(cliente.getIdCliente());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		
		if(!clienteOBd.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  usuario con Id " + cliente.getNombre() +" no existe en la BD");
		}
		
		ClienteEntity clienteDB = clienteOBd.get();
		
		validarParmetros(clienteOBd, clienteDB);
		
		try {
			clienteDB = clienteRepository.save(clienteDB);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}
		
		SingleResponse<ClienteEntity> response = new SingleResponse<>();

		response.setOk(true);
		response.setMensaje("Se ha actualizado al usuario " + clienteDB.getNombre() +" exitosamente.");
		response.setResponse(clienteDB);
		return response;
	}

	private void validarParmetros(Optional<ClienteEntity> clienteOBd, ClienteEntity clienteDB) {
		
		if (clienteDB.getNombre() != null) {
			clienteDB.setNombre(clienteDB.getNombre());
		}
		if (clienteDB.getApellidoPaterno() != null) {
			clienteDB.setApellidoPaterno(clienteDB.getApellidoPaterno());
		}
		if (clienteDB.getApellidoMaterno() != null) {
			clienteDB.setApellidoMaterno(clienteDB.getApellidoMaterno());
		}
		if (clienteDB.getCorreoElectronico() != null) {
			Optional<ClienteEntity> clienteOo = Optional.empty();
			try {
				clienteOo = clienteRepository.findBycorreoElectronicoIgnoreCase(clienteDB.getCorreoElectronico());
			} catch (DataAccessException ex) {
				log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
						ex.getStackTrace());
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Error al consultar los usuarios en la BD");
			}
			if (clienteOo.isPresent()) {
				throw new BusinessException(HttpStatus.BAD_REQUEST,
						"El  correo electrónico " + clienteDB.getCorreoElectronico() + " ya existe en la BD");
			}
			clienteDB.setCorreoElectronico("pedro2@gmail.com");
		}
		if (clienteDB.getEstatus() != null) {
			clienteDB.setEstatus(clienteDB.getEstatus());
		}
		
		if(clienteDB.getPassword()!= null) {
			clienteDB.setPassword(clienteDB.getPassword());
		}
	}

}
