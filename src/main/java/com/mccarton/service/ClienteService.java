package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.repository.IClienteRepository;

@Service
public class ClienteService implements IClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private IClienteRepository clienteRepository;

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

		cliente.setApellidoMaterno("Martinez");
		cliente.setApellidoPaterno("Rodriguez");
		cliente.setCorreoElectronico("pedro@gmail.com");
		cliente.setEstatus(1);
		cliente.setNombre("Pedro");
		cliente.setPassword("Pedro123");
		cliente.setTelefono("121232313");

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

	@Override
	public SingleResponse<ClienteEntity> eliminarCliente(Integer id) {
		SingleResponse<ClienteEntity> response = new SingleResponse<>();
		Optional<ClienteEntity> clienteO = Optional.empty();

		try {
			clienteO = clienteRepository.findById(id);
			if (clienteO.isPresent()) {
				clienteRepository.delete(clienteO.get());
				response.setOk(true);
				response.setMensaje("Cliente eliminado exitosamente");
				response.setResponse(clienteO.get());
				return response;
			} else {
				log.error("No se pudo encontrar al cliente con el ID proporcionado");
			}

		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de usuarios en la BD");
		
	}

}
