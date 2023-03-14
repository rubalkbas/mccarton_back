package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.MaterialesEntity;

public interface IMaterialesService {
	
	SingleResponse<List<MaterialesEntity>> consultarMateriales();
	SingleResponse<MaterialesEntity> crearMateriales(MaterialesEntity mat);
	SingleResponse<List<MaterialesEntity>> consultarMaterialesActivos();
	SingleResponse<MaterialesEntity> actualizarMaterial(MaterialesEntity mat);

}
