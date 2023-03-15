package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CategoriasEntity;

public interface ICategoriaService {

	SingleResponse<List<CategoriasEntity>> consultarCategorias();
	SingleResponse<CategoriasEntity> guardarCategoria(CategoriasEntity categoria);
	SingleResponse<CategoriasEntity> actualizarCategoria(CategoriasEntity categoria);

}