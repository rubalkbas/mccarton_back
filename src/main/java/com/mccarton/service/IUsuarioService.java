package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.UsuarioEntity;

public interface IUsuarioService {
	SingleResponse<List<UsuarioEntity>> consultarUsuarios();
	public SingleResponse<UsuarioEntity> crearUsuario(UsuarioEntity usuarios);
	public SingleResponse<UsuarioEntity> actualizarUsuario(UsuarioEntity usuarios);

}
