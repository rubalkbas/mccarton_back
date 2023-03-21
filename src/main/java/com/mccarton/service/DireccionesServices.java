package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;
import com.mccarton.model.entity.UsuarioEntity;
import com.mccarton.repository.IClienteRepository;
import com.mccarton.repository.IDireccionRepository;

@Service
public class DireccionesServices implements IDireccionesServices {

	@Autowired
	IDireccionRepository direccionesRepository;

	@Autowired
	IClienteRepository clienteRepository;

	public static final Integer ESTATUS_ACTIVO = 1;
	public static final Integer ESTATUS_INACTIVO = 0;
	private static final Logger log = LoggerFactory.getLogger(DireccionesServices.class);

	@Transactional
	@Override
	public SingleResponse<DireccionEntity> crearDireccion(ClienteDireccion clienteDireccion, ClienteEntity cliente) {

		Optional<ClienteEntity> clienteO = Optional.empty();
		try {
			clienteO = clienteRepository.findById(cliente.getIdCliente());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los usuarios en la BD");
		}

		DireccionEntity direccionEntity = new DireccionEntity();

		direccionEntity.setCalle(clienteDireccion.getCalle());
		direccionEntity.setCiudad(clienteDireccion.getCiudad());
		direccionEntity.setCodigoPostal(clienteDireccion.getCodigoPostal());
		direccionEntity.setColonia(clienteDireccion.getColonia());
		direccionEntity.setEntreCalle1(clienteDireccion.getEntreCalle1());
		direccionEntity.setEntreCalle2(clienteDireccion.getEntreCalle2());
		direccionEntity.setEstatus(ESTATUS_ACTIVO);
		direccionEntity.setNombreDireccion(clienteDireccion.getNombreDireccion());
		direccionEntity.setNumeroExterior(clienteDireccion.getNumeroExterior());
		direccionEntity.setNumeroInterior(clienteDireccion.getNumeroInterior());
		direccionEntity.setPredeterminado(ESTATUS_ACTIVO);
		direccionEntity.setTelefono(clienteDireccion.getTelefono());
		direccionEntity.setCliente(clienteO.get());

		try {
			direccionesRepository.save(direccionEntity);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la direccion en la BD");
		}

		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado la informacion de la direccion exitosamente");
		response.setResponse(direccionEntity);
		return response;
	}

	@Transactional
	@Override
	public SingleResponse<List<DireccionEntity>> consultarDireccionesPorCliente(Integer idCliente) {

		List<DireccionEntity> oDireccionesDb = new ArrayList<>();

		try {
			oDireccionesDb = direccionesRepository.findByDireccionClienteTodas(idCliente);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar la direccion en la BD");
		}

		if (!oDireccionesDb.isEmpty()) {
			SingleResponse<List<DireccionEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la dirección exitosamente");
			response.setResponse(oDireccionesDb);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron la direccion en la BD");
	}

	@Transactional
	@Override
	public SingleResponse<DireccionEntity> eliminarDireccion(Integer id) {
		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		Optional<DireccionEntity> direccionO = Optional.empty();

		try {
			direccionO = direccionesRepository.findById(id);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
		}

		try {
			direccionesRepository.delete(direccionO.get());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar la direccion en la BD");
		}

		response.setOk(true);
		response.setMensaje("Direccion eliminada exitosamente");
		response.setResponse(direccionO.get());
		return response;

	}

	@Transactional
	@Override
	public SingleResponse<DireccionEntity> crearNuevaDireccion(DireccionEntity direccion, ClienteEntity idCliente) {

		DireccionEntity dirAct = new DireccionEntity();

		try {
			dirAct = direccionesRepository.findBDireccionPredeterminada(idCliente.getIdCliente());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error al consultar la direccion con el id de cliente  en la BD");
		}

		if (dirAct != null) {
			dirAct.setPredeterminado(ESTATUS_INACTIVO);
			dirAct.setEstatus(ESTATUS_INACTIVO);
		}

		List<DireccionEntity> dir = new ArrayList<DireccionEntity>();

		try {
			dir = direccionesRepository.findByDireccionCliente(idCliente.getIdCliente(), PageRequest.of(0, 1));
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error al consultar la direccion con el id de cliente  en la BD");
		}

		if (!dir.isEmpty()) {

			DireccionEntity direccionEntity = new DireccionEntity();

			direccionEntity.setCalle(direccion.getCalle());
			direccionEntity.setCiudad(direccion.getCiudad());
			direccionEntity.setCodigoPostal(direccion.getCodigoPostal());
			direccionEntity.setColonia(direccion.getColonia());
			direccionEntity.setEntreCalle1(direccion.getEntreCalle1());
			direccionEntity.setEntreCalle2(direccion.getEntreCalle2());
			direccionEntity.setEstatus(direccion.getEstatus());
			direccionEntity.setNombreDireccion(direccion.getNombreDireccion());
			direccionEntity.setNumeroExterior(direccion.getNumeroExterior());
			direccionEntity.setNumeroInterior(direccion.getNumeroInterior());
			direccionEntity.setPredeterminado(ESTATUS_ACTIVO);
			direccionEntity.setTelefono(direccion.getTelefono());
			direccionEntity.setCliente(idCliente);

			try {
				direccionesRepository.save(direccionEntity);
			} catch (DataAccessException ex) {
				log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
						ex.getStackTrace());
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error guardad la direccion en la BD");
			}
		} else {
			throw new BusinessException(HttpStatus.BAD_REQUEST,
					"La direccion con el id " + idCliente.getIdCliente() + " No fue encontrado");
		}

		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado al usuario exitosamente.");
		response.setResponse(direccion);
		return response;
	}

	@Transactional
	@Override
	public SingleResponse<DireccionEntity> consultarDireccionDefecto(Integer idCliente) {
		DireccionEntity dirAct = new DireccionEntity();
		try {
			dirAct = direccionesRepository.findBDireccionPredeterminada(idCliente);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error al consultar la direccion con el id de cliente  en la BD");
		}

		if (dirAct != null) {
			SingleResponse<DireccionEntity> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la direccion exitosamente");
			response.setResponse(dirAct);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de direcciones en la BD");
	}

	@Transactional
	@Override
	public SingleResponse<DireccionEntity> actualizarDireccion(DireccionEntity direccion, ClienteEntity idCliente) {
		DireccionEntity dirAct = new DireccionEntity();
		try {
			dirAct = direccionesRepository.findBDireccionPredeterminada(idCliente.getIdCliente());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,
					"Error al consultar la direccion con el id de cliente  en la BD");
		}

		if (dirAct == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST,
					"La direccion con Id " + idCliente.getIdCliente() + " no existe en la BD");
		}

		if (direccion.getCalle() != null) {
			dirAct.setCalle(direccion.getCalle());
		}
		if (direccion.getCiudad() != null) {
			dirAct.setCiudad(direccion.getCiudad());
		}
		if (direccion.getCodigoPostal() != null) {
			dirAct.setCodigoPostal(direccion.getCodigoPostal());
		}
		if (direccion.getColonia() != null) {
			dirAct.setColonia(direccion.getColonia());
		}
		if (direccion.getEntreCalle1() != null) {
			dirAct.setEntreCalle1(direccion.getEntreCalle1());
		}
		if (direccion.getEntreCalle2() != null) {
			dirAct.setEntreCalle2(direccion.getEntreCalle2());
		}
		if (direccion.getNombreDireccion() != null) {
			dirAct.setNombreDireccion(direccion.getNombreDireccion());
		}
		if (direccion.getNumeroExterior() != null) {
			dirAct.setNumeroExterior(direccion.getNumeroExterior());
		}
		if (direccion.getNumeroInterior() != null) {
			dirAct.setNumeroInterior(direccion.getNumeroInterior());
		}
		if (direccion.getTelefono() != null) {
			dirAct.setTelefono(direccion.getTelefono());
		}

		try {
			direccionesRepository.save(dirAct);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error guardad la direccion en la BD");
		}

		SingleResponse<DireccionEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado al usuario exitosamente.");
		response.setResponse(direccion);
		return response;
	}

}
