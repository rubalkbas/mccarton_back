package com.mccarton.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.OrdenesEntity;
import com.mccarton.repository.IOrdenRepository;

@Service
public class OrdenesService implements IOrdenesService{
	
	
	private static final Logger log = LoggerFactory.getLogger(OrdenesService.class);
	
	@Autowired
	private IOrdenRepository ordenRepository;


	@Transactional
	@Override
	public SingleResponse<Page<OrdenesEntity>> consultarPorPaginas(int noPagina, String campo, String direccion,
			String buscar) {
		int pageSize = 10;
		Pageable pageable = PageRequest.of(noPagina - 1, pageSize,
				direccion.equalsIgnoreCase("asc") ? Sort.by(campo).ascending() : Sort.by(campo).descending());

		Page<OrdenesEntity> ordenPage = Page.empty();

		try {
			if (buscar != null) {
				ordenPage = ordenRepository.findAll(buscar, pageable);
			} else {
				ordenPage = ordenRepository.findAll(pageable);
			}

		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar las órdenes en la BD");
		}

		if (!ordenPage.isEmpty()) {
			SingleResponse<Page<OrdenesEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de órdenes exitosamente");
			response.setResponse(ordenPage);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros en la página " + noPagina);
	}

}
