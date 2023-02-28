package com.incottech.Sistema_General.controller;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incottech.Sistema_General.domain.entity.MaestraProducto;
import com.incottech.Sistema_General.model.ProductosImg;
import com.incottech.Sistema_General.model.Respuesta;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.incottech.Sistema_General.domain.entity.AuxResenaProducto;
import com.incottech.Sistema_General.domain.entity.AuxiliarImgProducto;
import com.incottech.Sistema_General.domain.entity.ColorProducto;
import com.incottech.Sistema_General.domain.entity.Imagen;
import com.incottech.Sistema_General.domain.entity.ProductoImagen;
import com.incottech.Sistema_General.domain.entity.tamanoProducto;
import com.incottech.Sistema_General.repository.AuxiliarImgProductoRepository;
import com.incottech.Sistema_General.repository.ColorRepository;
import com.incottech.Sistema_General.repository.MaestraProductoRepository;
import com.incottech.Sistema_General.repository.tamanoRepository;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/producto")
public class ProductoController {
	

private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	
	@Autowired
	private MaestraProductoRepository maestraProductoRepository;
	
	@Autowired
	private AuxiliarImgProductoRepository auxiliarImgProductoRepository;

	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private tamanoRepository TamanoRepository;
	
	
	@GetMapping(value = "/consultaTodosProductos")
	public  ResponseEntity<Respuesta> consultaTodosProductos() {
		
		LOGGER.info("Entra a controller para consulta lista de todos los Productos");
	
		Respuesta response = new Respuesta();
		List<MaestraProducto> producto;
		List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>(); //Lista que guarda imagenes relacionadas con los productos
		List<ProductosImg> listaProductos = new ArrayList<>();
		
		producto = maestraProductoRepository.consultaTodosProductos();
		auxiliarImgProducto = auxiliarImgProductoRepository.findAll();
		
		
		for(MaestraProducto mp : producto) {
			
			ProductosImg pi = new ProductosImg();
			
			pi.setIdProducto(mp.getIdProducto());
			pi.setName(mp.getName());
			pi.setOldPrice(mp.getOldPrice());
			pi.setNewPrice(mp.getNewPrice());
			pi.setDiscount(mp.getDiscount());
			pi.setRatingsCount(mp.getRatingsCount());
			pi.setRatingsValue(mp.getRatingsValue());
			pi.setDescription(mp.getDescription());
			pi.setAvailibilityCount(mp.getAvailibilityCount());
			pi.setCartCount(mp.getCartCount());
			pi.setCategoryId(mp.getCategoryId());
			pi.setStatus(mp.getStatus());
			pi.setFechaAlta(mp.getFechaAlta());
			pi.setWeight(mp.getWeight());
			pi.setCondicion(mp.getCondicion());
			pi.setIdCatMarca(mp.getIdCatMarca());			
			
			for(AuxiliarImgProducto aip : auxiliarImgProducto) {
				
				if(mp.getIdProducto() == aip.getIdProducto() ) {
					
					pi.setImagenProducto(aip.getSmall());
					
					break;
					
				}
				
			}
			
			listaProductos.add(pi);
			
		}		
		
		response.setLista(listaProductos);
		response.setMensaje("Consulta exitosa");
		
		LOGGER.info("Sale de controller para consulta de todos los Productos");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/listaProductos")
	public  ResponseEntity<Respuesta> listaProductosImagen(
														@RequestParam (name = "seccion") String seccion, 
														@RequestParam (name = "idCategoria") Long idCategoria,
														@RequestParam (name = "idProducto") Long idProducto)  throws ParseException {
		
		LOGGER.info("Entra a controller para consulta lista de Productos.");
	
		Respuesta response = new Respuesta();

		List <MaestraProducto> maestraProducto = new ArrayList<>(); //Lista que guarda productos
		List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>(); //Lista que guarda imagenes relacionadas con los productos
		List <ColorProducto> colorProducto = new ArrayList<>(); //Lista que guarda los colores relacionada con el producto
		List <tamanoProducto> TamanoProducto = new ArrayList<>(); //Lista que guarda los tamanos relacionada con el producto
		
		List <ProductoImagen> productoImagen = new ArrayList<>(); //el que se manda en response
				 
		//consulta segun la seccion 
		if( seccion.equals("destacadas") ) {  //Destacados
			
			maestraProducto = maestraProductoRepository.consultaProductosDestacados();
			
		} else if( seccion.equals("descuento") ) { //Descuento
			
			maestraProducto = maestraProductoRepository.consultaProductosDescuento(); 
			
		} else if( seccion.equals("mejorCalificados") ) {  //los mas valorados
			
			List <MaestraProducto> valorados = new ArrayList<>(); 
			
			valorados = maestraProductoRepository.consultaProductosValorados(); 
			
			for(MaestraProducto valor : valorados) {
				int caliV;
				int caliC;
				int cali;
				caliV = valor.getRatingsValue() ;
				caliC = valor.getRatingsCount();
				
				if( (caliV == 0 && caliC == 0)  || (caliV == 0 && caliC != 0) || (caliV != 0 && caliC == 0)) {
					cali = 0;
				}else {
					cali = (caliV / caliC);
				}
				
				
				if(cali >= 80) {
					
					maestraProducto.add(valor);
					
				}			
				
			}
			
		} else if( seccion.equals("recienAgregados") ) {  //los recien llegados
			
			List <MaestraProducto> recien = new ArrayList<>(); 
			
			Date fecha = new Date();

		     SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd"); 
		     
		     String f = objSDF.format(fecha);		     
		     Date fechaInicial = objSDF.parse(f);

			recien = maestraProductoRepository.consultaProductosValorados();

			for(MaestraProducto valor : recien) {
				
				String ff = objSDF.format(valor.getFechaAlta());				
				Date fechaFinal = objSDF.parse(ff);
			    int dias=(int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000);
			    
			    if(dias >= -15) {
	
			    	maestraProducto.add(valor);
			    	
			    }

			}
			
		} else if( seccion.equals("relacionados") ) {  //los relacionados
			
			maestraProducto = maestraProductoRepository.consultaProductosRelacionados(idCategoria, idProducto);
			
		}
		
		auxiliarImgProducto = auxiliarImgProductoRepository.findAll(); //Consulta imagenes
		
		colorProducto = colorRepository.findAll(); //Consulta colores
		
		TamanoProducto = TamanoRepository.findAll(); //Consulta tamanos
				
		for(MaestraProducto vpi : maestraProducto) {
			
			ProductoImagen pi = new ProductoImagen(); //inicializa producto imagen
			
			pi.setIdProducto(vpi.getIdProducto());
			pi.setName(vpi.getName());
			pi.setOldPrice(vpi.getOldPrice());
			pi.setNewPrice(vpi.getNewPrice());
			pi.setDiscount(vpi.getDiscount());
			pi.setRatingsCount(vpi.getRatingsCount());
			pi.setRatingsValue(vpi.getRatingsValue());
			pi.setDescription(vpi.getDescription());
			pi.setAvailibilityCount(vpi.getAvailibilityCount());
			pi.setCartCount(vpi.getCartCount());
			pi.setCategoryId(vpi.getCategoryId());
			pi.setStatus(vpi.getStatus());
			pi.setFechaAlta(vpi.getFechaAlta());
			pi.setWeight(vpi.getWeight());
			pi.setCondicion(vpi.getCondicion());
			pi.setIdCatMarca(vpi.getIdCatMarca());
		
			
			///////// iniciaiza imagen para llenar lista 
			
			List <Imagen> imagen = new ArrayList<>(); 		
			
			for(AuxiliarImgProducto vpi2 : auxiliarImgProducto) {
				
				if( vpi.getIdProducto() == vpi2.getIdProducto() ) {
					
					Imagen i =  new Imagen(); //inicializa
					
					i.setSmall(vpi2.getSmall());
					i.setMedium(vpi2.getMedium());
					i.setBig(vpi2.getBig());
					
					imagen.add(i);
					
				}					
				
			}				
			
			pi.setImages(imagen); // lista imagenes del producto	
			
			////////////////////////////////////////////////
			
			///////// iniciaiza color para llenar lista 
			
			List <String> colores = new ArrayList<>(); 		
			
			for(ColorProducto cp : colorProducto) {
				
				if( vpi.getIdProducto() == cp.getIdProducto() ) {
					
					colores.add( cp.getCodigoColor());
					
				}				
				
			}	
			
			pi.setColor(colores);
			
			////////////////////////////////////////////////
			
			///////// iniciaiza tamano para llenar lista 
			
			List <String> tamanos = new ArrayList<>(); 		
			
			for(tamanoProducto tp : TamanoProducto) {
				
				if( vpi.getIdProducto() == tp.getIdProducto() ) {
					
					tamanos.add( tp.getCodigoTamano());
					
				}			
				
			}	
			
			pi.setSize(tamanos);
			
			////////////////////////////////////////////////
			
			productoImagen.add(pi);		// lista de productos con imagenes, colores

		}
		
		response.setLista(productoImagen);
		response.setMensaje("Consulta exitosa de productos");
		
		LOGGER.info("Sale de controller para consulta lista de Productos.");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	
	@PostMapping(value = "/listaProductosCategoria")
	public  ResponseEntity<Respuesta> listaProductosCategoria(@RequestParam (name = "idNivelCategoria") Integer idNivelCategoria) {
		
		LOGGER.info("Entra a controller para consulta de Productos por Categoria padre.");
		
		Respuesta response = new Respuesta();
		
		List <MaestraProducto> maestraProducto = new ArrayList<>();
		List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>();
		List <ProductoImagen> productoImagen = new ArrayList<>();
		List <ColorProducto> colorProducto = new ArrayList<>(); //Lista que guarda los colores relacionada con el producto
		List <tamanoProducto> TamanoProducto = new ArrayList<>(); //Lista que guarda los tamanos relacionada con el producto
		
		
		maestraProducto = maestraProductoRepository.listaProductosCategoria(idNivelCategoria); 
		
		auxiliarImgProducto = auxiliarImgProductoRepository.findAll(); //Recuperar -- consulta imagenes
		
		colorProducto = colorRepository.findAll(); //Consulta colores
		
		TamanoProducto = TamanoRepository.findAll(); //Consulta tamanos

		for(MaestraProducto vpi : maestraProducto) {
			
			ProductoImagen pi = new ProductoImagen();
			
			pi.setIdProducto(vpi.getIdProducto());
			pi.setName(vpi.getName());
			pi.setOldPrice(vpi.getOldPrice());
			pi.setNewPrice(vpi.getNewPrice());
			pi.setDiscount(vpi.getDiscount());
			pi.setRatingsCount(vpi.getRatingsCount());
			pi.setRatingsValue(vpi.getRatingsValue());
			pi.setDescription(vpi.getDescription());
			pi.setAvailibilityCount(vpi.getAvailibilityCount());
			pi.setCartCount(vpi.getCartCount());
			pi.setCategoryId(vpi.getCategoryId());
			pi.setStatus(vpi.getStatus());
			pi.setFechaAlta(vpi.getFechaAlta());
			pi.setCondicion(vpi.getCondicion());
			pi.setIdCatMarca(vpi.getIdCatMarca());
			
			///////// iniciaiza imagen para llenar lista 
			
			List <Imagen> imagen = new ArrayList<>();	
			
			for(AuxiliarImgProducto vpi2 : auxiliarImgProducto) {
				
				if( vpi.getIdProducto() == vpi2.getIdProducto() ) {
					
					Imagen i =  new Imagen();
					
					i.setSmall(vpi2.getSmall());
					i.setMedium(vpi2.getMedium());
					i.setBig(vpi2.getBig());
					
					imagen.add(i);
					
				}					
				
			}				
			
			pi.setImages(imagen);	
			
			////////////////////////////////////////////////
			
	///////// iniciaiza color para llenar lista 
			
				List <String> colores = new ArrayList<>(); 		
				
				for(ColorProducto cp : colorProducto) {
					
					if( vpi.getIdProducto() == cp.getIdProducto() ) {
						
						colores.add( cp.getCodigoColor());
						
					}				
					
				}	
				
				pi.setColor(colores);
				
				////////////////////////////////////////////////
				
				///////// iniciaiza tamano para llenar lista 
				
				List <String> tamanos = new ArrayList<>(); 		
				
				for(tamanoProducto tp : TamanoProducto) {
					
					if( vpi.getIdProducto() == tp.getIdProducto() ) {
						
						tamanos.add( tp.getCodigoTamano());
						
					}			
					
				}	
				
				pi.setSize(tamanos);
				
				////////////////////////////////////////////////
				
			
			productoImagen.add(pi);		

		}
		
		response.setLista(productoImagen);
		response.setMensaje("Consulta exitosa de productos");
		
		LOGGER.info("Sale de controller para consulta lista de Productos.");
		
		return new ResponseEntity<>(response, HttpStatus.OK);


	}
	
	
	@PostMapping(value = "/productoDetalle")
	public  ResponseEntity<Respuesta> consultaImagenesProductoPorID(@RequestParam (name = "idProducto") Long idProducto) {
		
		LOGGER.info("Entra a controller para consultar producto imagen detalle.");
	
		Respuesta response = new Respuesta();
		
	    MaestraProducto maestraProducto; //info del producto
	    List <AuxiliarImgProducto> auxiliarImgProducto = new ArrayList<>(); //Lista que guarda imagenes relacionada con el producto
	    List <ColorProducto> colorProducto = new ArrayList<>(); //Lista que guarda los colores relacionada con el producto
	    List <tamanoProducto> TamanoProducto = new ArrayList<>(); //Lista que guarda los tamanos relacionada con el producto
	    
		ProductoImagen productoImagen = new ProductoImagen(); // dto que va al response
	    
	    maestraProducto = maestraProductoRepository.consultaProductoPorID(idProducto);  // info producto por id
	    
	    auxiliarImgProducto = auxiliarImgProductoRepository.consultaImagenesProductoPorID(idProducto); //Consulta imagenes de producto
	    
	    colorProducto = colorRepository.consultaColorProductoPorID(idProducto); //Consulta colores de producto
	    
	    TamanoProducto = TamanoRepository.consultaTamanoProductoPorID(idProducto); //Consulta tamanos de producto
		
	    productoImagen.setIdProducto(maestraProducto.getIdProducto());
	    productoImagen.setName(maestraProducto.getName());
	    productoImagen.setOldPrice(maestraProducto.getOldPrice());
	    productoImagen.setNewPrice(maestraProducto.getNewPrice());
	    productoImagen.setDiscount(maestraProducto.getDiscount());
	    productoImagen.setRatingsCount(maestraProducto.getRatingsCount());
	    productoImagen.setRatingsValue(maestraProducto.getRatingsValue());
	    productoImagen.setDescription(maestraProducto.getDescription());
	    productoImagen.setAvailibilityCount(maestraProducto.getAvailibilityCount());
	    productoImagen.setCartCount(maestraProducto.getCartCount());
	    productoImagen.setCategoryId(maestraProducto.getCategoryId());
	    productoImagen.setStatus(maestraProducto.getStatus());
	    productoImagen.setFechaAlta(maestraProducto.getFechaAlta());
	    productoImagen.setWeight(maestraProducto.getWeight());
	    productoImagen.setCondicion(maestraProducto.getCondicion());
	    productoImagen.setIdCatMarca(maestraProducto.getIdCatMarca());
	    
	    ///////// iniciaiza imagen para llenar lista 
	    
	    List <Imagen> imagen = new ArrayList<>(); 		
		
		for(AuxiliarImgProducto aip : auxiliarImgProducto) {
			
			Imagen i =  new Imagen();
			
			i.setSmall(aip.getSmall());
			i.setMedium(aip.getMedium());
			i.setBig(aip.getBig());
			
			imagen.add(i);				
			
		}				
		
		productoImagen.setImages(imagen);
		
		////////////////////////////////////////////////
		
		///////// iniciaiza colores para llenar lista 
		
		List <String> colores = new ArrayList<>(); 		
		
		for(ColorProducto cp : colorProducto) {
			
			colores.add( cp.getCodigoColor());				
			
		}	
		
		productoImagen.setColor(colores);
		
		////////////////////////////////////////////////
		
		///////// iniciaiza tamano para llenar lista 
		
		List <String> tamanos = new ArrayList<>(); 		
		
		for(tamanoProducto tp : TamanoProducto) {
			
			tamanos.add( tp.getCodigoTamano());				
			
		}	
		
		productoImagen.setSize(tamanos);
		
		////////////////////////////////////////////////
		
		response.setDto(productoImagen);
		response.setMensaje("Consulta exitosa del producto");
		
		LOGGER.info("Sale de controller para consulta Producto imagen detalle.");
		
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/registroProducto")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody ProductoImagen producto) {
		
		LOGGER.info("Entra a controller para guardar de Colonos..");
		Respuesta response = new Respuesta();
		
		Date hoy = new Date();
		
		MaestraProducto productoReg = new MaestraProducto();
		
		productoReg.setAvailibilityCount(producto.getAvailibilityCount());
		productoReg.setDescription(producto.getDescription());
		productoReg.setFechaAlta(hoy);
		productoReg.setName(producto.getName());
		productoReg.setOldPrice(producto.getOldPrice());
		productoReg.setNewPrice(producto.getNewPrice());
		productoReg.setCategoryId(producto.getCategoryId());
		productoReg.setDiscount(producto.getDiscount());
		productoReg.setStatus(1);
		productoReg.setCondicion(1);// cambiame perra
		productoReg.setIdCatMarca(producto.getCategoryId()); // cambiame perra
		
		maestraProductoRepository.save(productoReg);
		
		Long idProducto = productoReg.getIdProducto();
		
		
		List <?>colores = new ArrayList<>(); 
		colores = producto.getColor();		
		Iterator<?> it = colores.iterator();
		
		while (it.hasNext()) {
	    Object item = it.next();
		
			
		ColorProducto nuevoColor = new ColorProducto();
		nuevoColor.setStatus("1");	
		nuevoColor.setCodigoColor(item.toString());
		nuevoColor.setDescripcion("Color  Prueba");
		nuevoColor.setIdProducto(idProducto);
		colorRepository.save(nuevoColor);
		
		}
		
		
		List <?>size = new ArrayList<>(); 
		size = producto.getSize();		
		Iterator<?> itSize = size.iterator();
		
		while (itSize.hasNext()) {
	    Object item = itSize.next();
	    
	    tamanoProducto nuevoTamano = new tamanoProducto();
	    nuevoTamano.setCodigoTamano(item.toString());
	    nuevoTamano.setStatus("1");
	    nuevoTamano.setIdProducto(idProducto);
	    nuevoTamano.setDescripcion("Tamaño prueba");
	    
	    TamanoRepository.save(nuevoTamano);
	    

		}
		
		
		
		List <?>imagenes = new ArrayList<>(); 
		imagenes = producto.getImages();		
		Iterator<?> itImage = imagenes.iterator();
	
		while (itImage.hasNext()) {
	    Object item = itImage.next();
	    
	    
	    AuxiliarImgProducto nuevaImagen = new AuxiliarImgProducto();
	  
	    nuevaImagen.setIdProducto(idProducto);
	    nuevaImagen.setBig(item.toString());
	    nuevaImagen.setMedium(item.toString());
	    nuevaImagen.setSmall(item.toString());
	    
	    auxiliarImgProductoRepository.save(nuevaImagen);
	    
		}
	

		LOGGER.info("Continua registro en historico estado de cuenta..");
		

		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	

}
