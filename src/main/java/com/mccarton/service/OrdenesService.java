package com.mccarton.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mccarton.exceptions.BusinessException;
import com.mccarton.model.dto.CrearOrdenRequest;
import com.mccarton.model.dto.EstatusOrden;
import com.mccarton.model.dto.OrdenDto;
import com.mccarton.model.dto.ResponseListarCarrito;
import com.mccarton.model.dto.SingleResponse;
import com.mccarton.model.entity.ClienteEntity;
import com.mccarton.model.entity.DireccionEntity;
import com.mccarton.model.entity.OrdenDetalleEntity;
import com.mccarton.model.entity.OrdenesEntity;
import com.mccarton.repository.ICarroComprasRepository;
import com.mccarton.repository.IOrdenDetalleRepository;
import com.mccarton.repository.IOrdenRepository;

@Service
public class OrdenesService implements IOrdenesService{
	
	
	private static final Logger log = LoggerFactory.getLogger(OrdenesService.class);
	
	@Autowired
	private ICarritoCompraService carritoCompraService;
	
	@Autowired
	private IDireccionesServices direccionesServices;
	
	@Autowired
	private ICarroComprasRepository carroComprasRepository;
	
	
	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Autowired
	private IOrdenDetalleRepository ordenDetalleRepository;


	@Transactional
	@Override
	public SingleResponse<Page<OrdenesEntity>> consultarPorPaginas(int noPagina, String campo, String direccion,
			String buscar) {
		int pageSize = 10;
		Pageable pageable = PageRequest.of(noPagina - 1, pageSize,
				direccion.equalsIgnoreCase("asc") ? Sort.by(campo).ascending() : Sort.by(campo).descending());

		Page<OrdenesEntity> ordenPage = Page.empty();

		try {
			if (buscar != null) {
				ordenPage = ordenRepository.findAll(buscar, pageable);
			} else {
				ordenPage = ordenRepository.findAll(pageable);
			}

		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar las órdenes en la BD");
		}

		if (!ordenPage.isEmpty()) {
			SingleResponse<Page<OrdenesEntity>> response = new SingleResponse<>();
			response.setOk(true);
			response.setMensaje("Se ha obtenido la lista de órdenes exitosamente");
			response.setResponse(ordenPage);
			return response;
		}
		throw new BusinessException(HttpStatus.BAD_REQUEST, "No se encontraron registros en la página " + noPagina);
	}


	@Transactional
	@Override
	public SingleResponse<OrdenDto> detalleOrden(Integer idOrden) {
		Optional<OrdenesEntity> oOrden = Optional.empty();
		
		try {
			oOrden = ordenRepository.findById(idOrden);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al consultar las órdenes en la BD");
		}
		
		if(oOrden.isEmpty()) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "Orden con id " + idOrden + " no fue encontrada en la BD");
		}
		SingleResponse<OrdenDto> response = new SingleResponse<>();
		OrdenesEntity orden = oOrden.get();
		OrdenDto ordenDto = new OrdenDto();
		ordenDto.setCliente(orden.getCliente());
		ordenDto.setDetalles(orden.getOrdenDetalle());
		ordenDto.setCalle(orden.getCalle());
		ordenDto.setCiudad(orden.getCiudad());
		ordenDto.setCodigoPostal(orden.getCodigoPostal());
		ordenDto.setColonia(orden.getColonia());
		ordenDto.setEntreCalle1(orden.getEntreCalle1());
		ordenDto.setEntreCalle2(orden.getEntreCalle2());
		ordenDto.setEstatusOrden(orden.getEstatusOrden());
		ordenDto.setFechaOrden(orden.getFechaOrden());
		ordenDto.setIdOrden(idOrden);
		ordenDto.setImpuesto(orden.getImpuesto());
		ordenDto.setMetodoPago(orden.getMetodoPago());
		ordenDto.setNumeroExterior(orden.getNumeroExterior());
		ordenDto.setNumeroInterior(orden.getNumeroInterior());
		ordenDto.setSubTotal(orden.getSubTotal());
		ordenDto.setTelefono(orden.getTelefono());
		ordenDto.setTotal(orden.getTotal());
//		orden.setOrdenes(orden.getOrdenes());
		response.setOk(true);
		response.setMensaje("Se obtuvo el detalle de la orden exitosamente");
		response.setResponse(ordenDto);
		return response;
	}


	@Transactional
	@Override
	public SingleResponse<OrdenDto> crearOrden(CrearOrdenRequest request) {
		DireccionEntity direccionCompra = direccionesServices.consultarDireccionePorId(request.getIdDireccion()).getResponse();
		ResponseListarCarrito carrito = carritoCompraService.mostrarCarrito(request.getIdCliente()).getResponse();
		ClienteEntity cliente = carrito.getCarrito().get(0).getCliente();
		OrdenesEntity nuevaOrden = new OrdenesEntity();
		nuevaOrden.setCalle(direccionCompra.getCalle());
		nuevaOrden.setCiudad(direccionCompra.getCiudad());
		nuevaOrden.setCliente(cliente);
		nuevaOrden.setCodigoPostal(direccionCompra.getCodigoPostal());
		nuevaOrden.setColonia(direccionCompra.getColonia());
		nuevaOrden.setEntreCalle1(direccionCompra.getEntreCalle1());
		nuevaOrden.setEntreCalle2(direccionCompra.getEntreCalle2());
		nuevaOrden.setEstatusOrden(EstatusOrden.NUEVO);
		nuevaOrden.setFechaOrden(LocalDateTime.now());
		nuevaOrden.setImpuesto(request.getIva());
		nuevaOrden.setMetodoPago(request.getMetodoPago());
		nuevaOrden.setNumeroExterior(direccionCompra.getNumeroExterior());
		nuevaOrden.setNumeroInterior(direccionCompra.getNumeroInterior());
		nuevaOrden.setSubTotal(request.getTotalProductos());
		nuevaOrden.setTelefono(direccionCompra.getTelefono());
		nuevaOrden.setTotal(request.getPagoTotal());
		
		try {
			nuevaOrden = ordenRepository.save(nuevaOrden);
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar la orden en la BD");
		}
		OrdenesEntity ordenGuardada = nuevaOrden;
		Set<OrdenDetalleEntity> detallesOrden = new HashSet<>();
		carrito.getCarrito().forEach(carroProducto->{
			OrdenDetalleEntity ordenDetalle = new OrdenDetalleEntity();
			ordenDetalle.setCantidad(carroProducto.getCantidad());
			ordenDetalle.setOrden(ordenGuardada);
			ordenDetalle.setPrecio(carroProducto.getProducto().getPrecioVenta());
			ordenDetalle.setProducto(carroProducto.getProducto());
			ordenDetalle.setSubtotal(carroProducto.getSubtotal());
			try {
				detallesOrden.add(ordenDetalleRepository.save(ordenDetalle));
				
			} catch (DataAccessException ex) {
				log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
						ex.getStackTrace());
				throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el datelle de la orden en la BD");
			}
		});
		ordenGuardada.setOrdenDetalle(detallesOrden);
		
		try {
			carroComprasRepository.deleteByCliente(ordenGuardada.getCliente().getIdCliente());
		} catch (DataAccessException ex) {
			log.error("Ha ocurrido un error inesperado. Exception {} {}", ex.getMessage() + " " + ex,
					ex.getStackTrace());
			throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al borrar el carrito de compras en la BD");
		}
		
		return detalleOrden(ordenGuardada.getIdOrden());
	}

}
