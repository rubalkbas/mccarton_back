package com.mccarton.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mccarton.model.dto.CheckOutInfo;
import com.mccarton.model.dto.ResponseListarCarrito;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.DireccionEntity;


@Service
public class CheckOutService implements ICheckOutService{
	
	
	private static final Logger log = LoggerFactory.getLogger(CheckOutService.class);

	@Autowired
	private IDireccionesServices direccionesServices;
	
	@Autowired
	private ICarritoCompraService carritoCompraService;
	

	@Override
	public SingleResponse<CheckOutInfo> prepararCheckOut(Integer idCliente) {
		log.info("Entrada al service CheckOut");
		DireccionEntity direccionPredeterminada = direccionesServices.consultarDireccionDefecto(idCliente).getResponse();
		ResponseListarCarrito carrito = carritoCompraService.mostrarCarrito(idCliente).getResponse();
		CheckOutInfo checkOutInfo = new CheckOutInfo();
		checkOutInfo.setCarrito(carrito);
		double totalProductos, iva, totalCompra;
		totalProductos = carrito.getTotalEstimado();
		iva = carrito.getTotalEstimado() * 0.16;
		totalCompra = totalProductos + iva;
		checkOutInfo.setTotalProductos(totalProductos);
		checkOutInfo.setIva(iva);
		checkOutInfo.setPagoTotal(totalCompra);
		checkOutInfo.setDireccion(direccionPredeterminada);
		SingleResponse<CheckOutInfo> response = new SingleResponse<>();
		response.setMensaje("Check Out Info");
		response.setOk(true);
		response.setResponse(checkOutInfo);
		return response;
	}

}
