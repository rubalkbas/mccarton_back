package com.mccarton.service;

import org.springframework.data.domain.Page;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.OrdenesEntity;


public interface IOrdenesService {
	
	SingleResponse<Page<OrdenesEntity>> consultarPorPaginas(int noPagina, String campo, String direccion, String buscar);

}
