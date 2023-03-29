package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ColoresEntity;

public interface IColoresService {
	
	SingleResponse<List<ColoresEntity>> consultarColores();
	SingleResponse<ColoresEntity> crearColor(ColoresEntity color);
	SingleResponse<List<ColoresEntity>> consultarColoresActivos();
	SingleResponse<ColoresEntity> actualizarEstatusColor(ColoresEntity color);
	SingleResponse<ColoresEntity>actuzalizarColor(ColoresEntity color);
	

}
