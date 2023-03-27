package com.mccarton.service;



import com.mccarton.model.dto.CarroComprasRequest;
import com.mccarton.model.dto.ResponseListarCarrito;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.CarroComprasEntity;


public interface ICarritoCompraService {
	
	SingleResponse<CarroComprasEntity> agregarProducto(CarroComprasRequest request);
	SingleResponse<ResponseListarCarrito> mostrarCarrito(Integer idCliente);
	SingleResponse<ResponseListarCarrito> actualizarCantidad(CarroComprasRequest request);
	SingleResponse<ResponseListarCarrito> eliminarProducto(Integer idCarrito);

}
