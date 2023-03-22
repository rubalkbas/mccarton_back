package com.mccarton.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CategoriasEntity;

public interface ICategoriaService {

	SingleResponse<List<CategoriasEntity>> consultarCategorias();
	SingleResponse<CategoriasEntity> guardarCategoria(CategoriasEntity categoria);
	SingleResponse<CategoriasEntity> actualizarCategoria(CategoriasEntity categoria);
	SingleResponse<CategoriasEntity> actualizarEstatusCategoria(Integer idCategoria, Integer estatus);
	SingleResponse<List<CategoriasEntity>> consultarCategoriasActivas();
	SingleResponse<Page<CategoriasEntity>>consultarPorPaginas(Integer numeroPagina,Integer tamanoPagina, String campo,String campoBusqueda, String direccion);
	SingleResponse<List<CategoriasEntity>> consultarPorIdCategoriaPadre(Integer idPadre);
	SingleResponse<List<CategoriasEntity>> consultarCategoriasPadres();
	
}