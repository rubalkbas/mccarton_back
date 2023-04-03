package com.mccarton.service;

import java.util.List;

import com.mccarton.model.dto.CheckOutInfo;
import com.mccarton.model.dto.ResponseListarCarrito;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CarroComprasEntity;

public interface ICheckOutService {
	
	SingleResponse<CheckOutInfo> prepararCheckOut(Integer idClientes);

}
