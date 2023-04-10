package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ReseniaEntity;

public interface IReseniaService {
	
	public SingleResponse<ReseniaEntity> crearResenia(ReseniaEntity resenia);
	SingleResponse<List<ReseniaEntity>> consultarResenia();
	SingleResponse<ReseniaEntity> consultarReseniaClienteProducto(Integer idCliente,Integer idProducto);
	SingleResponse<List<ReseniaEntity>> consultarReseniaCliente(Integer idCliente);
	SingleResponse<ReseniaEntity> consultarReseniaDetalle(Integer idResenia);
	SingleResponse<ReseniaEntity> actualizarResenia(ReseniaEntity resenia);
	SingleResponse<ReseniaEntity>  borrarResenia(Integer idResenia);
}
