/**
 * 
 */
package com.incottech.Sistema_General.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.incottech.Sistema_General.domain.entity.OrdenCompra;
import com.incottech.Sistema_General.domain.entity.OrdenCompraDetalle;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.OrdenCompraDetalleRepository;
import com.incottech.Sistema_General.repository.OrdenCompraRepository;

/**
 * @author Rubalkbas
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ordenCompra")
public class OrdenCompraController {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrdenCompraController.class);
	
	
	@Autowired
	OrdenCompraRepository ordenCompraRepository;
	
	@Autowired
	OrdenCompraDetalleRepository ordenCompraDetalleRepository;
	
	
	@PostMapping(value = "/guardaOrdenComrpa")
	public ResponseEntity<Respuesta> guardaOrdenComrpa(@Valid @RequestBody OrdenCompra ordenCompra )  {

		Respuesta response = new Respuesta();
		
		LOGGER.info("Entra a controller para guardar la Orden de Compra!!!");
		ordenCompraRepository.eliminaOrdenCompraIdUsuaerio(ordenCompra.getIdUsuario());
		ordenCompra.setEstatusOrdenCompra("PENDIENTE PAGO");
		ordenCompraRepository.save( ordenCompra );
		
		Long x = ordenCompra.getId();
		response.setDto(x);
		response.setMensaje("Orden 	Compra Registrado Correctamente!");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value = "/guardaOrdenComrpaDetalle")
	public ResponseEntity<Respuesta> guardaOrdenComrpaDetalle(@Valid @RequestBody OrdenCompraDetalle ordenCompraDetalle )  {

		Respuesta response = new Respuesta();
		
		LOGGER.info("Entra a controller para guardar el detalle de la Orden de Compra!!!");
		
		ordenCompraDetalleRepository.save(ordenCompraDetalle);
		response.setMensaje("Orden 	Compra Registrado Correctamente!");
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping(value = "/consultaOrdenesServicio",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaOrdenesServicio() {
		
		LOGGER.info("Entra a controller para consulta lista de ordenes de servicio");
	
		Respuesta response = new Respuesta();
		List <OrdenCompra> listaOrdenComprar;
		
		listaOrdenComprar = ordenCompraRepository.findAll();
		
		response.setLista(listaOrdenComprar);
		response.setMensaje("Consulta exitosa");
		
		LOGGER.info("Sale de controller para consulta lista de ordenes de servicio");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/consultaOrdenServicoId")
	public ResponseEntity<Respuesta> consultaDetalleOrdenServico(
			@RequestParam (name = "idOrdenCompra") Long idOrdenCompra) {

		LOGGER.info("Entra a controller para consulta  orden de servicio por id !!!");
		
		Respuesta response = new Respuesta();
		
		OrdenCompra ordenCompra = new OrdenCompra();
		
		ordenCompra = ordenCompraRepository.consultaOrdenCompraID(idOrdenCompra);

		response.setDto(ordenCompra);
		response.setMensaje("Consulta exitosa");

		LOGGER.info("Sale de controller para consulta  orden de servicio por detalle !!!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "/consultaDetalleOrdenServicoId")
	public ResponseEntity<Respuesta> consultaDetalleOrdenServicoId(
			@RequestParam (name = "idOrdenCompra") Long idOrdenCompra) {

		LOGGER.info("Entra a controller para consulta  orden de servicio con detalle por id !!!");
		
		Respuesta response = new Respuesta();

		List <OrdenCompraDetalle> ordenCompraDetalle;
		
		ordenCompraDetalle = ordenCompraDetalleRepository.consultaOrdenCompraDetalleID(idOrdenCompra);

		response.setLista(ordenCompraDetalle);
		response.setMensaje("Consulta exitosa");

		LOGGER.info("Sale de controller para consulta  orden de servicio con detalle por id!!!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**Metodo para listar los productos que estan en la lista de deseos**/
	@GetMapping(value = "/consultaOrdenCompletada",  produces = MediaType.APPLICATION_JSON_VALUE )
	ResponseEntity<Respuesta> consultaOrdenCompletada(@RequestParam (name = "idUsuario") String idUsuario) {
		
		LOGGER.info("Entra a controller para consultar las ordenes completadas..");
		
		Respuesta response = new Respuesta();
		List <OrdenCompra> listaOrdenComprar;	
		
		listaOrdenComprar = ordenCompraRepository.consultaOrdenesCompletas(idUsuario);
		
		

		if (listaOrdenComprar.isEmpty()) {
			response.setMensaje("No existen ordenes completadas para este usuario");
		}else {
			response.setMensaje("Consulta exitosa");
			response.setLista(listaOrdenComprar);
		}
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	

	@PostMapping(value = "/productoOrdenRecolectado")
	public ResponseEntity<Respuesta> productoOrdenRecolectado(
			@RequestParam (name = "idOrdenCompra") Long idOrdenCompra, @RequestParam (name = "idProduto") Long idProduto) {

		LOGGER.info("Entra a controller para actualizar estatus a recolectado del rpoducto en  orden de servicio !!!");
		
		Respuesta response = new Respuesta();
		
		ordenCompraDetalleRepository.productoOrdenRecolectado(idOrdenCompra, idProduto);

		response.setMensaje("Consulta exitosa");

		LOGGER.info("Sale de controller para ctualizar estatus a recolectado del rpoducto en  orden de servicio!!!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	/** Metodo para agregar el domicilio de la compra*/
	 @PostMapping(value = "/agregarDomicilio/")
		public ResponseEntity<Respuesta> agregarDomicilio(@RequestParam (name = "idD") int idD, @RequestParam (name = "idOC") int idOC) {
			 
		 Respuesta response = new Respuesta();
		
		
		 ordenCompraRepository.agregarDomicilio(idD,idOC);
		 
			 
		 response.setMensaje("Se agrego correctamente el domicilio");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 /** Metodo para agregar codigo de rastreo a orden de compra */
	 @PostMapping(value = "/agregarCodigoRastro")
		public ResponseEntity<Respuesta> agregarCodigoRastro(
				@RequestParam (name = "idOrdenCompra") Long idOrdenCompra, @RequestParam (name = "codRastreo") String codRastreo) {
			 
		 	LOGGER.info("Entra a controller para actualizar estatus a recolectado del rpoducto en  orden de servicio !!!");
			
			Respuesta response = new Respuesta();
			
			ordenCompraRepository.agregarCodigoRastro(idOrdenCompra, codRastreo);

			response.setMensaje("Consulta exitosa");

			LOGGER.info("Sale de controller para ctualizar estatus a recolectado del rpoducto en  orden de servicio!!!");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		 
		}
	 

	
	
}
