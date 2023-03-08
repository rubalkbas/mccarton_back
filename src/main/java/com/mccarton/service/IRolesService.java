package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.RolEntity;

public interface IRolesService {
	
	SingleResponse<List<RolEntity>> consultarRoles();
	SingleResponse<RolEntity> crearRoles(RolEntity rol);
	SingleResponse<List<RolEntity>> consultarRolesActivos();
	SingleResponse<RolEntity> actualizarEstatusRol(RolEntity rol);

}
