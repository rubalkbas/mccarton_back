package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;

public interface IDireccionesServices {

	public SingleResponse<DireccionEntity> crearDireccion(ClienteDireccion direccion, ClienteEntity cliente);
	SingleResponse<List<DireccionEntity>> consultarDireccionesPorCliente(Integer idCliente);
	SingleResponse<DireccionEntity> eliminarDireccion(Integer id);
	SingleResponse<DireccionEntity> crearNuevaDireccion(DireccionEntity cliente, ClienteEntity idCliente);
	SingleResponse<DireccionEntity> consultarDireccionDefecto(Integer idCliente);
	public SingleResponse<DireccionEntity> actualizarDireccion(DireccionEntity direccion,ClienteEntity idCliente);
	SingleResponse<DireccionEntity> consultarDireccionePorId(Integer direccionid);
}
