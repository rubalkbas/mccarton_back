package com.incottech.Sistema_General.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.MaestraCategoria;
import com.incottech.Sistema_General.model.CategoriaPadreHija;
import com.incottech.Sistema_General.model.Respuesta;
import com.incottech.Sistema_General.repository.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping(value = "/consultaCategoriaTodo",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaCategoriaTodo() {
		
		LOGGER.info("Entra a controller para consulta lista de Categoria");
	
		Respuesta response = new Respuesta();
		List <MaestraCategoria> maestraCategoria;
		
		maestraCategoria = categoriaRepository.consultaCategoriaTodo();

		response.setMensaje("Consulta exitosa");
		response.setLista(maestraCategoria);
		
		LOGGER.info("Sale de controller para consulta lista de Categoria");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping(value = "/consultaCategoriaSinDuplicado",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaCategoriaSinDuplicado() {
		
		LOGGER.info("Entra a controller para consulta lista de Categoria sin duplicados");
	
		Respuesta response = new Respuesta();
		List <MaestraCategoria> maestraCategoria;
		
		maestraCategoria = categoriaRepository.consultaCategoriaSinDuplicado();

		response.setMensaje("Consulta exitosa");
		response.setLista(maestraCategoria);
		
		LOGGER.info("Sale de controller para consulta lista de Categoria sin duplicados");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping(value = "/consultaPadreHijaN1",  produces = MediaType.APPLICATION_JSON_VALUE )
	public  ResponseEntity<Respuesta> consultaPadreHijaN1() {
		
		LOGGER.info("Entra a controller para consulta lista de Categoria con sus hijas nivel 1");
	
		Respuesta response = new Respuesta();
		List <MaestraCategoria> maestraCategoria0;
		List <MaestraCategoria> maestraCategoriahijaN1;
		List <MaestraCategoria> maestraCategoriahijaN2;
		
		maestraCategoria0 = categoriaRepository.consultaCategoria0();
		maestraCategoriahijaN1 = categoriaRepository.consultaCategoriaTodo();
		maestraCategoriahijaN2 = categoriaRepository.consultaCategoriaTodo();
		
		List <CategoriaPadreHija> categoriaPadreHija = new ArrayList<>();
		 

		for(MaestraCategoria mc : maestraCategoria0) {
			
			if(mc.getNivelPadre() == 0 ) {
				
				CategoriaPadreHija cph = new CategoriaPadreHija();
				
				cph.setId(mc.getNivelcategoria());
				cph.setName(mc.getDescCategoria());
				cph.setHasSubCategory(true);
				cph.setParentId(mc.getNivelPadre());

				
				categoriaPadreHija.add(cph); //agrega categoria padre 
				
				for(MaestraCategoria mc1 : maestraCategoriahijaN1) {
					
					if(mc.getNivelcategoria() == mc1.getNivelPadre()) {
						
						CategoriaPadreHija cph1 = new CategoriaPadreHija();
						
						cph1.setId(mc1.getNivelcategoria());
						cph1.setName(mc1.getDescCategoria());
						cph1.setHasSubCategory(true);
						cph1.setParentId(mc.getNivelcategoria());
						
						int nivel = mc1.getNivelcategoria();
						
						
						
						categoriaPadreHija.add(cph1); //agrega categoria hijas N1
						
						for(MaestraCategoria mc2 : maestraCategoriahijaN2) {
							
							if(nivel == mc2.getNivelPadre()) {
								
								CategoriaPadreHija cph2 = new CategoriaPadreHija();
								
								cph2.setId(mc2.getNivelcategoria());
								cph2.setName(mc2.getDescCategoria());
								cph2.setHasSubCategory(false);
								cph2.setParentId(mc1.getNivelcategoria());
								
								categoriaPadreHija.add(cph2); //agrega categoria hijas N2
								
							}
							
						}
						
						
						
					}
					
				}
				
			}

		}
	
		response.setMensaje("Consulta exitosa");
		response.setLista(categoriaPadreHija);
		
		
		LOGGER.info("Sale de controller para consulta lista de Categoria con sus hijas nivel 1");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
