package com.mccarton.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CategoriasEntity;
import com.mccarton.repository.ICategoriaRepository;

@Service
public class CategoriasService implements ICategoriaService {

	private static final Logger log = LoggerFactory.getLogger(CategoriasService.class);
	private static final Integer ESTATUS = 1;
	
	@Autowired
	private ICategoriaRepository categoriaRepository;
	
	@Override
	public SingleResponse<List<CategoriasEntity>> consultarCategorias() {
		List<CategoriasEntity> listaCategorias = new ArrayList<CategoriasEntity>();
		try {
			listaCategorias = categoriaRepository.findAll();			
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado. Excepcion {} {}");
			excepcion.getStackTrace();
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al consultar las categorias en la base de datos");
		}
		if(!listaCategorias.isEmpty()) {
			SingleResponse<List<CategoriasEntity>> response = new SingleResponse<List<CategoriasEntity>>();
			response.setOk(true);
			response.setMensaje("Se obtuvo las categorias con exito");
			response.setResponse(listaCategorias);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron categorias en la Base de datos");
		
	}

	@Transactional
	@Override
	public SingleResponse<CategoriasEntity> guardarCategoria(CategoriasEntity categoria) {
		Optional<CategoriasEntity> categoriaOpcional = Optional.empty();
		
		try {
			categoriaOpcional = categoriaRepository.findByNombreCategoriaIgnoreCase(categoria.getNombreCategoria());
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado {} {} ",excepcion.getMessage() + " " + excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar las categorias en la BD");		
		}
		if(categoriaOpcional.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "La categoria " + categoria.getNombreCategoria() + "ya existe en la BD");
		}
		categoria.setEstatus(1);
		try {
			 categoria = categoriaRepository.save(categoria);
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado. Excepcion {} {}",excepcion.getMessage() + "" + excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la categoria en la Base de Datos");
		}
		SingleResponse<CategoriasEntity> response = new SingleResponse<CategoriasEntity>();
		response.setOk(true);
		response.setMensaje("Se ha guardado la categoria" + categoria.getNombreCategoria() + "correctamente");
		response.setResponse(categoria);		
		return response;
	}

	@Override
	public SingleResponse<CategoriasEntity> actualizarCategoria(CategoriasEntity categoria) {
		
		Optional<CategoriasEntity> categoriaOpcional = Optional.empty();
		
		try {
			categoriaOpcional = categoriaRepository.findById(categoria.getIdCategorias());	
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado. Excepcion {} {}", excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al encontrar la categoria en la Base de Datos");
		}
		
		if(!categoriaOpcional.isPresent()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "La categoria con ID" + categoria.getIdCategorias() + "no se encontro en la base de datos");
		}
		
		
		CategoriasEntity categoriaActualizado = categoriaOpcional.get();					
		categoriaActualizado.setCodigoReferencia(categoria.getCodigoReferencia());
		categoriaActualizado.setDescripcionCategoria(categoria.getDescripcionCategoria());
		categoriaActualizado.setDetallesCategoria(categoria.getDetallesCategoria());
		categoriaActualizado.setEstatus(categoria.getEstatus());
		categoriaActualizado.setIdCategoriaPadre(categoria.getIdCategoriaPadre());
		categoriaActualizado.setNombreCategoria(categoria.getNombreCategoria());	
		try {
			categoriaActualizado = categoriaRepository.save(categoriaActualizado);						
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error Inesperado Excepcion {} {} ", excepcion.getMessage() + "" , excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la categoria");
		}
		
		SingleResponse<CategoriasEntity> response =  new SingleResponse<CategoriasEntity>();
		response.setOk(true);
		response.setMensaje("La categoria se ha actualizado");
		response.setResponse(categoriaActualizado);
		
		return response;
	}

	@Override
	public SingleResponse<CategoriasEntity> actualizarEstatusCategoria(CategoriasEntity categoria) {
		
		Optional<CategoriasEntity> categoriaOpcional = Optional.empty();
							
		try {
			categoriaOpcional = categoriaRepository.findById(categoria.getIdCategorias());
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado. Excepcion {} {}", excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR,"Error al encontrar la categoria en la Base de Datos");
		}
		
		if(categoriaOpcional.isEmpty()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "La Categoria con el ID: "+ categoria.getIdCategorias() + "no se encontro");
		}
				
		CategoriasEntity categoriaBorrado = categoriaOpcional.get();
		categoriaBorrado.setEstatus(categoria.getEstatus());
		
		try {
			categoriaBorrado = categoriaRepository.save(categoriaBorrado);			
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado. Excepcion {} {}", excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la categoria");
		}
		
		SingleResponse<CategoriasEntity> response = new SingleResponse<CategoriasEntity>();
		
		response.setMensaje("La categoria se elimino correctamente");
		response.setOk(true);
		response.setResponse(categoriaBorrado);
		return response;
	}

	@Override
	public SingleResponse<List<CategoriasEntity>> consultarCategoriasActivas() {
		List<CategoriasEntity> listaCategoria = new ArrayList<CategoriasEntity>();
		try {
			listaCategoria = categoriaRepository.findByEstatus(ESTATUS);		
		} catch (DataAccessException excepcion) {
			log.error("Ha ocurrido un error inesperado {} {} ",excepcion.getMessage() + " " + excepcion,
					excepcion.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar las categorias en la BD");		
		}
		
		if(!listaCategoria.isEmpty()) {
			SingleResponse<List<CategoriasEntity>> response = new SingleResponse<List<CategoriasEntity>>();
			response.setOk(true);
			response.setMensaje("Se obtuvo las categorias con exito");
			response.setResponse(listaCategoria);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron categorias en la Base de datos");
				
	}

}
