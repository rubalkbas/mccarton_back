package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.CarroComprasRequest;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CarroComprasEntity;

public interface ICarritoCompraService {
	
	SingleResponse<List<CarroComprasEntity>> agregarProducto(CarroComprasRequest request);

}
