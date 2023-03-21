package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.UsuarioEntity;

public interface IClienteService {
	
	SingleResponse<List<ClienteEntity>> consultarClientes();
	SingleResponse<ClienteEntity> consultarClientePorId(Integer clienteid);
	SingleResponse<ClienteEntity> crearCliente(ClienteDireccion clienteDireccion);
	SingleResponse<ClienteEntity> eliminarCliente(Integer id);
	SingleResponse<ClienteEntity> actualizarCliente(ClienteEntity cliente);

}
