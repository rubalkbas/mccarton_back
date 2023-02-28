package com.incottech.Sistema_General.controller;

import java.util.Date;
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

import com.incottech.Sistema_General.domain.entity.ListaDeseoProducto;
import com.incottech.Sistema_General.domain.entity.ListaDeseos;
//import com.incottech.Sistema_General.domain.entity.ParametrosListaDeseos;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.ListaDeseosProductoRepository;
import com.incottech.Sistema_General.repository.ListaDeseosRepository;

/**
 * @author Mauricio Soto
 * @version 1.0
 * @since   2021-01-06
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/listaDeseos")
public class ListaDeseosController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgBannerController.class);
	
	@Autowired
	private ListaDeseosRepository listaDeseosRepository;
	
	@Autowired
	private ListaDeseosProductoRepository listaDeseosProductoRepository;
	
	/**Metodo para agregar productos a la lista de deseos**/
	@PostMapping("/agregarProdLista")
	public ResponseEntity<?> agregarProdLista(@Valid @RequestBody ListaDeseos prodList) {
		
		Long estatus = (long) 1;
		Date hoy = new Date();
		LOGGER.info("Entra a controller para agregar un producto a la lista..");
		
		Respuesta response = new Respuesta();
		
		prodList.setEstatus(estatus);
		prodList.setFechaRegistro(hoy);
		
			listaDeseosRepository.save(prodList);
			response.setEstatus("OK");
			response.setMensaje("Producto Agregado Exitosamente");
			return new ResponseEntity<>(response, HttpStatus.OK);
		
		
	}
	
	/**Metodo para listar los productos que estan en la lista de deseos**/
	@GetMapping(value = "/consultaListaDeseo",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaListaDeseo(@RequestParam (name = "idUsuario") String idUsuario) {
		
		LOGGER.info("Entra a controller para consultar la lista de deseos..");
		
		Respuesta response = new Respuesta();
		List<ListaDeseos> listaProducto;	
		
		listaProducto = listaDeseosRepository.consultaWishList(idUsuario);
		
		

		if (listaProducto.isEmpty()) {
			response.setMensaje("VACIO");
		}else {
			response.setMensaje("Consulta exitosa");
			response.setLista(listaProducto);
		}
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	/**Metodo para listar los productos que estan en la lista de deseos**/
	@GetMapping(value = "/listaProdListaDeseoUsu",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> listaProdListaDeseoUsu(@RequestParam (name = "id") int id) {
		
		LOGGER.info("Entra a controller para consultar los productos de la lista de deseos..");
		Long idUsu = (long) id;
		Respuesta response = new Respuesta();
		List<ListaDeseoProducto> listaProducto;	
		
		listaProducto = listaDeseosProductoRepository.traerProductUsu(idUsu);
		
		if (listaProducto.isEmpty()) {
			response.setMensaje("VACIO");
		}else {
			response.setLista(listaProducto);
			response.setMensaje("Consulta exitosa");
		}
				
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	 /** Metodo para eliminar una de los productos de la lista de deseos */
	 @PostMapping(value = "/removerProductoDeseado/")
		public ResponseEntity<Respuesta> removerProductoDeseado
		(@RequestParam (name = "idUsu") int idUsuario, @RequestParam (name = "idProd") int idProducto) {
			
			LOGGER.info("Entra a controller para eliminar la imagen");
			Long idUsuarioL = (long) idUsuario;
			Long idProductoL = (long) idProducto;
			Respuesta response = new Respuesta();
			listaDeseosRepository.removerProducto(idProductoL,idUsuarioL);
			
			response.setMensaje("Borrado exitoso");

			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 /** Metodo para eliminar una de los productos de la lista de deseos */
	 @PostMapping(value = "/removerTodosLosProductos/")
		public ResponseEntity<Respuesta> removerTodosLosProductos
		(@RequestParam (name = "id") int id) {
			Long idUsuario = (long) id;
			LOGGER.info("Entra a controller para eliminar la imagen");
			
			Respuesta response = new Respuesta();
			listaDeseosRepository.removerTodosLosProducto(idUsuario);
			
			response.setMensaje("Borrado exitoso");

			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	  
	
}
