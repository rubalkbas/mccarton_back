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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.incottech.Sistema_General.domain.entity.ImgBanner;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.BannerRepository;



/**
 * @author Mauricio Soto
 * @version 1.0
 * @since 2020-12-28
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banner")
public class ImgBannerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImgBannerController.class);
	
	@Autowired
	private BannerRepository bannerRepository;
	
	/**Metodo para agregar imagenes asignadas al banner**/
	@PostMapping("/agregarImgBanner")
	public ResponseEntity<?> agregarImgBanner(@Valid @RequestBody ImgBanner banner) {
		
		Long estatus = (long) 1;
		Date hoy = new Date();
		LOGGER.info("Entra a controller para guardar las imagenes..");
		
		Respuesta response = new Respuesta();
		
		//banner.getEstatus();
		banner.setEstatus(estatus);
		banner.setFechaRegistro(hoy);
 
		bannerRepository.save(banner);
		
		response.setEstatus("OK");
		response.setMensaje("Imagen Agregada Exitosamente");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**Metodo para listar las imagenes asignadas al banner**/
	@GetMapping(value = "/listaImgBanner",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaImgBanner() {
		
		LOGGER.info("Entra a controller para consultar las imagenes..");
		
		Respuesta response = new Respuesta();
		List<ImgBanner> banner;	
		
		banner = bannerRepository.findAll();
		
		
		response.setEstatus("OK");
		response.setMensaje("Consulta exitosa");
		response.setLista(banner);
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	 /** Metodo para eliminar una de las imagenes asignadas al banner */
	 @PostMapping(value = "/borrarImgBanner/")
		public ResponseEntity<Respuesta> borrarImgBanner(
				@RequestParam (name = "id") int id) {
			
		 	Long idImg = (long) id;
			LOGGER.info("Entra a controller para eliminar la imagen");
			
			Respuesta response = new Respuesta();
			
			bannerRepository.deleteById(idImg);
			
			response.setMensaje("Borrado exitoso");

			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 
	 /** Metodo para editar el estatus de la imagen asignadas al banner*/
	 @PostMapping(value = "/estatusImgBanner/")
		public ResponseEntity<Respuesta> estatusImgBanner(
				@RequestParam (name = "id") int id) {
			 
		 Respuesta response = new Respuesta();
		 String us = bannerRepository.getEstatusImgBanner(id);
		 if(us.equals("1")) {
			 bannerRepository.editEstatusInactivo(id);
		 }else if(us.equals("0")) {
			 bannerRepository.editEstatusActivo(id);
		 }
			 
		 response.setMensaje("Edicion de estatus exitosa");
		 return new ResponseEntity<>(response, HttpStatus.OK);
		}
	 
	 /** Metodo para consultar un imagen por su ID*/
	 @PostMapping(value = "/traerImgBannerById/" )
		public  ResponseEntity<Respuesta> traerImgBannerById(@RequestParam (name = "id") Long id) {
			
			LOGGER.info("Entra a controller para consultar un Usuario..");
		
			
			Respuesta response = new Respuesta();
			ImgBanner imgBanner = new ImgBanner();	
			
			imgBanner = bannerRepository.getAllImgBanner(id);
			
			response.setEstatus("OK");
			response.setMensaje("Consulta exitosa");
			response.setDto(imgBanner);
			
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
	 /** Metodo para editar una imagen por su ID*/
	 @PutMapping(value = "/editarImgBanner/")
		public ResponseEntity<Respuesta> editarImgBanner(@RequestBody ImgBanner imgBanner) {
		 
		bannerRepository.save(imgBanner);
		 
		 Respuesta response = new Respuesta();
		 
		 response.setMensaje("Edicion exitosa");
		 return new ResponseEntity<>(response, HttpStatus.OK);
			
		}
	 /**Metodo para listar las imagenes asignadas al banner con estatus activo de manera aleatoria**/
	 @GetMapping(value = "/consultaProductosAleatorios",  produces = MediaType.APPLICATION_JSON_VALUE )
		public ResponseEntity<Respuesta> consultaProductosAleatorios() {
			
			LOGGER.info("Entra a controller para consulta lista de Imagen Banners..");
		
			Respuesta response = new Respuesta();
			List <ImgBanner> imagenBanner;
			
			imagenBanner = bannerRepository.consultaProductosAleatorios();

			response.setMensaje("Consulta exitosa");
			response.setLista(imagenBanner);
			
			return new ResponseEntity<>(response, HttpStatus.OK);

		}
	 
	 /**Metodo para listar las imagenes asignadas al banner con estatus activo
		@GetMapping(value = "/listaImgBannerActivos",  produces = MediaType.APPLICATION_JSON_VALUE )
		public  ResponseEntity<Respuesta> listaImgBannerActivos() {
			
			LOGGER.info("Entra a controller para consultar las imagenes..");
			
			Respuesta response = new Respuesta();
			List<ImgBanner> banner;	
			
			banner = bannerRepository.findActivos();
			
			
			response.setEstatus("OK");
			response.setMensaje("Consulta exitosa");
			response.setLista(banner);
			
			return new ResponseEntity<>(response, HttpStatus.OK);

		}**/
	
}
