package com.incottech.Sistema_General.controller;

import java.util.ArrayList;
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

import com.incottech.Sistema_General.domain.entity.AuxResenaProducto;
import com.incottech.Sistema_General.domain.entity.AuxiliarImgProducto;
import com.incottech.Sistema_General.domain.entity.MaestraProducto;
import com.incottech.Sistema_General.domain.entity.UsuariosInfo;
import com.incottech.Sistema_General.model.Resena;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.AuxResenaProductoRepository;
import com.incottech.Sistema_General.repository.AuxiliarImgProductoRepository;
import com.incottech.Sistema_General.repository.MaestraProductoRepository;
import com.incottech.Sistema_General.repository.UsuariosInfoRepository;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@CrossOrigin(origins = "**")
@RestController
@RequestMapping("/resena")
public class AuxResenaProductoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuxResenaProductoController.class);
	
	@Autowired
	private AuxResenaProductoRepository auxResenaProductoRepository;
	
	@Autowired
	private UsuariosInfoRepository usuariosInfoRepository;
	
	@Autowired
	private MaestraProductoRepository maestraProductoRepository;
	
	@Autowired
	private AuxiliarImgProductoRepository auxiliarImgProductoRepository;
	
	
	@GetMapping(value = "/consultaResenas",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaResenas() {
		
		LOGGER.info("Entra al controller para consulta lista de resenas");
	
		Respuesta response = new Respuesta();

		List <Resena> resena = new ArrayList<>(); //Lista de reseñas de los productos
		List <AuxResenaProducto> auxResenaProducto = new ArrayList<>(); //Lista de reseñas
		List <UsuariosInfo> usuariosInfo = new ArrayList<>(); //Lista info usuarios
		List <MaestraProducto> maestraProducto = new ArrayList<>(); //Lista que prodcutos
		List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>(); //Lista que guarda imagenes relacionadas con los productos
		
		auxResenaProducto = auxResenaProductoRepository.findAll();
		usuariosInfo = usuariosInfoRepository.findAll();
		maestraProducto = maestraProductoRepository.findAll();
		auxiliarImgProducto = auxiliarImgProductoRepository.findAll();
		
		for(AuxResenaProducto arp : auxResenaProducto) {
			
			Resena r = new Resena();
			
			r.setIdResenaProducto(arp.getIdResenaProducto());
			r.setIdUsuario(arp.getIdUsuario());
			r.setIdProducto(arp.getIdProducto());
			r.setComentario(arp.getComentario());
			r.setCalificacion(arp.getCalificacion());
			r.setEstatus(arp.getEstatus());
			r.setFechaAlta(arp.getFechaAlta());
			
			for(UsuariosInfo ui : usuariosInfo) { //obtiene nombre de usuarios
				
				if(arp.getIdUsuario() == ui.getIdUsuario()) {
					
					r.setNombre(ui.getNombreUsuario() + " " + ui.getApePa() + " " + ui.getApeMa());					
				}
				
			}		
			
			for(MaestraProducto mp : maestraProducto) { //obtiene nombre de productos
				
				if(arp.getIdProducto() == mp.getIdProducto() ) {
					
					r.setNombreProducto( mp.getName() );
					
				}
				
			}
			
			for(AuxiliarImgProducto aip : auxiliarImgProducto) {
				
				if(arp.getIdProducto() == aip.getIdProducto() ) {
					
					r.setImagenProducto( aip.getSmall());
					
					break;
					
				}
				
			}
			
			resena.add(r);
			
		}
		
		response.setLista(resena);
		response.setMensaje("Consulta exitosa");
		
		LOGGER.info("Sale del controller para consulta lista de resenas");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@PostMapping(value = "/consultaResenaPorIdProducto")
	public  ResponseEntity<Respuesta> consultaResenaPorIdProducto(@RequestParam (name = "idProducto") Long idProducto) {
		
		LOGGER.info("Entra a controller para consulta Resena Por Id Producto.");
		
		Respuesta response = new Respuesta();

		List <Resena> resena = new ArrayList<>(); //Lista de reseñas del producto
		List <AuxResenaProducto> auxResenaProducto = new ArrayList<>(); //Lista de reseñas
		List <UsuariosInfo> usuariosInfo = new ArrayList<>(); //Lista info usuarios
		List <MaestraProducto> maestraProducto = new ArrayList<>(); //Lista que prodcutos
		List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>(); //Lista que guarda imagenes relacionadas con los productos
		
		auxResenaProducto = auxResenaProductoRepository.consultaResenaPorIdProducto(idProducto);
		usuariosInfo = usuariosInfoRepository.findAll();
		maestraProducto = maestraProductoRepository.findAll();
		auxiliarImgProducto = auxiliarImgProductoRepository.findAll();
		
		for(AuxResenaProducto arp : auxResenaProducto) {
			
			Resena r = new Resena();
			
			r.setIdResenaProducto(arp.getIdResenaProducto());
			r.setIdUsuario(arp.getIdUsuario());
			r.setIdProducto(arp.getIdProducto());
			r.setComentario(arp.getComentario());
			r.setCalificacion(arp.getCalificacion());
			r.setEstatus(arp.getEstatus());
			r.setFechaAlta(arp.getFechaAlta());
			
			for(UsuariosInfo ui : usuariosInfo) {
				
				if(arp.getIdUsuario() == ui.getIdUsuario()) {
					
					r.setNombre(ui.getNombreUsuario() + " " + ui.getApePa() + " " + ui.getApeMa());					
				}
				
			}		
			
			for(MaestraProducto mp : maestraProducto) { //obtiene nombre de productos
				
				if(arp.getIdProducto() == mp.getIdProducto() ) {
					
					r.setNombreProducto( mp.getName() );
					
				}
				
			}
			
			for(AuxiliarImgProducto aip : auxiliarImgProducto) {
				
				if(arp.getIdProducto() == aip.getIdProducto() ) {
					
					r.setImagenProducto( aip.getSmall());
					
					break;
					
				}
				
			}
			
			resena.add(r);
			
		}

		response.setLista(resena);
		response.setMensaje("Consulta exitosa de resenas de productos");
		
		LOGGER.info("Sale de controller paraconsulta Resena Por Id Producto.");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	

	@PostMapping("/agregarResena")
	public ResponseEntity<?> agregarResena(@Valid @RequestBody AuxResenaProducto auxResenaProducto) {

		
		LOGGER.info("Entra a controller para agregar resena en producto");
		
		Respuesta response = new Respuesta();
		
		MaestraProducto maestraProducto; //Lista que guarda productos
		AuxResenaProducto dto;
		Date hoy = new Date();
		
		auxResenaProducto.setFechaAlta(hoy);// setea la fecha actual a la resena
		
		dto = auxResenaProductoRepository.save(auxResenaProducto);// guarda la resena
		
		maestraProducto = maestraProductoRepository.consultaProductoPorID(auxResenaProducto.getIdProducto());//obtiene producto que se agreo resena
		
		maestraProducto.setRatingsCount( maestraProducto.getRatingsCount() + 1 );//al producto recuperado, se agrega +1 al count
		maestraProducto.setRatingsValue( maestraProducto.getRatingsValue() + auxResenaProducto.getCalificacion()); //al producto recuperado, se agrega calificacion
		
		maestraProductoRepository.save(maestraProducto);//se actualiza producto
		
		response.setMensaje("Sale de controller para agregar resena en producto");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/** Metodo para editar el estatus de la resena*/
	 @PostMapping(value = "/estatusResena")
	public ResponseEntity<Respuesta> estatusResena(@RequestParam (name = "idResenaProducto") int idResenaProducto) {
		 
		 LOGGER.info("Entra a controller para actualizar estatus de resena");
			 
		 Respuesta response = new Respuesta();
		 
		 String valorEstatus = auxResenaProductoRepository.valorEstatusResena(idResenaProducto);
		 if(valorEstatus.equals("1")) {
			 auxResenaProductoRepository.editaEstatusInactivo(idResenaProducto);
			 response.setMensaje("Edicion de estatus inactivo exitosa");
		 }else if(valorEstatus.equals("0")) {
			 auxResenaProductoRepository.editaEstatusActivo(idResenaProducto);
			 response.setMensaje("Edicion de estatus activo exitosa");
		 }		 
		 
		 LOGGER.info("Sale de controller para actualizar estatus de resena");
		 
		 return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 
	 /** Metodo para editar el estatus de la resena*/
	 @PostMapping(value = "/resenaInactiva")
	public ResponseEntity<Respuesta> resenaInactiva(
			@RequestParam (name = "idProducto") Long idProducto,@RequestParam (name = "idUsuario") Long idUsuario) {
		 
		 LOGGER.info("Entra a controller para consultar si el usuario tiene resena inactiva del producto");
			 
		 Respuesta response = new Respuesta();
		 int valor = 0;
		 		 
		 valor = auxResenaProductoRepository.resenaInactiva(idProducto,idUsuario);
		 
		 if(valor == 0) {
			 response.setMensaje("Sin renesa bloqueada");
			 response.setValor("0");
		 }else {
			 response.setMensaje("Se encuentra resena bloqeuada");
			 response.setValor("1");
		 }		 
		 
		 LOGGER.info("Sale de controller para para consultar si el usuario tiene resena inactiva del producto");
		 
		 return new ResponseEntity<>(response, HttpStatus.OK);
		}

}
