package com.mccarton.service;

import org.springframework.data.domain.Page;

import com.mccarton.model.dto.CrearOrdenRequest;
import com.mccarton.model.dto.OrdenActualizarDTO;
import com.mccarton.model.dto.OrdenDto;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.OrdenesEntity;


public interface IOrdenesService {
	
	SingleResponse<Page<OrdenesEntity>> consultarPorPaginas(int noPagina, String campo, String direccion, String buscar);
	SingleResponse<OrdenDto> detalleOrden (Integer idOrden);
	SingleResponse<OrdenDto> crearOrden (CrearOrdenRequest request);
	SingleResponse<OrdenesEntity> eliminarOrden(Integer idOrden);
	SingleResponse<OrdenesEntity> actualizarOrden(OrdenActualizarDTO ordenActualizar);

	
}
