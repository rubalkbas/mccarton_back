package com.mccarton.service;

import java.time.LocalDateTime;
import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.OfertaEntity;

public interface IOfertaService {

	SingleResponse<List<OfertaEntity>> consultarTodosOferta();
	SingleResponse<OfertaEntity> guardarOferta(OfertaEntity oferta, Integer idProducto);
	SingleResponse<OfertaEntity> actualizarOferta(OfertaEntity oferta, Integer idProductoS);
	SingleResponse<List<OfertaEntity>> consultarTodosActivos();
	SingleResponse<OfertaEntity> actualizarOfertaEstatus(Integer idOferta, Integer estatus);
	void actualizarOfertasVencidas();
}
