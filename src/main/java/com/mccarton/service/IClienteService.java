package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;

public interface IClienteService {
	
	SingleResponse<List<ClienteEntity>> consultarCliente();
	SingleResponse<ClienteEntity> crearCliente(ClienteEntity cliente);
	SingleResponse<ClienteEntity> eliminarCliente(Integer id);
}
