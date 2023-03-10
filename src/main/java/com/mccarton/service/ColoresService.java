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
import com.mccarton.model.entity.ColoresEntity;
import com.mccarton.repository.IColorRepository;


@Service

public class ColoresService implements IColoresService{
	
	private static final Logger log = LoggerFactory.getLogger(ColoresService.class);
	public static final Integer ESTATUS_ACTIVO = 1;
	
	@Autowired
	private IColorRepository colorRepository;

	@Transactional
	@Override
	public SingleResponse<List<ColoresEntity>> consultarColores() {
		List<ColoresEntity> listaColores = new ArrayList<>();
		
		try {
			listaColores = colorRepository.findAll();
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los colores en la BD");
		}
		
		if(!listaColores.isEmpty()) {
			SingleResponse<List<ColoresEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de colores exitosamente");
			response.setResponse(listaColores);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de colores en la BD");
	
	}

	@Override
	public SingleResponse<ColoresEntity> crearColor(ColoresEntity color) {
		Optional<ColoresEntity> colorOp = Optional.empty();
		try {
			colorOp = colorRepository.findByNombreColorIgnoreCase(color.getNombreColor());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los color en la BD");
		}
		if(colorOp.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  color " + color.getNombreColor() +" ya existe en la BD");
		}
		color.setEstatus(1);
		try {
			color = colorRepository.save(color);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los colores en la BD");
		}
		SingleResponse<ColoresEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha guardado el color " + color.getNombreColor() +" exitosamente.");
		response.setResponse(color);
		return response;
	}

	@Override
	public SingleResponse<List<ColoresEntity>> consultarColoresActivos() {
		List<ColoresEntity> listaColores = new ArrayList<>();
		
		try {
			listaColores = colorRepository.findByEstatus(ESTATUS_ACTIVO);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los colores en la BD");
		}
		
		if(!listaColores.isEmpty()) {
			SingleResponse<List<ColoresEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de colores activos exitosamente");
			response.setResponse(listaColores);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros de colores activos en la BD");
	}

	@Override
	public SingleResponse<ColoresEntity> actualizarEstatusColor(ColoresEntity color) {
		Optional<ColoresEntity> colorOp = Optional.empty();
		try {
			colorOp = colorRepository.findById(color.getIdColor());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los colores en la BD");
		}
		if(!colorOp.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "El  color con Id " + color.getIdColor() +" no existe en la BD");
		}
		ColoresEntity colorUpdate = colorOp.get();
		colorUpdate.setEstatus(color.getEstatus());
		try {
			colorUpdate = colorRepository.save(colorUpdate);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar los colres en la BD");
		}
		SingleResponse<ColoresEntity> response = new SingleResponse<>();
		response.setOk(true);
		response.setMensaje("Se ha actualizado el color con Id" + colorUpdate.getIdColor() +" exitosamente.");
		response.setResponse(colorUpdate);
		return response;
	}

}
