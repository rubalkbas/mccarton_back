package com.mccarton.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mccarton.model.dto.ClienteDireccion;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;

public interface IClienteService {
	
	SingleResponse<List<ClienteEntity>> consultarClientes();
	SingleResponse<ClienteEntity> consultarClientePorId(Integer clienteid);
	SingleResponse<ClienteEntity> crearCliente(ClienteDireccion clienteDireccion);
	SingleResponse<ClienteEntity> eliminarCliente(Integer id);
	SingleResponse<ClienteEntity> actualizarCliente(ClienteEntity cliente);
	SingleResponse<List<ClienteEntity>> consultarClientesActivos();
	SingleResponse<Page<ClienteEntity>> consultarPorPaginas(int noPagina, String campo, String direccion, String buscar);
	SingleResponse<ClienteEntity> loginCliente(ClienteEntity usuario);
}
