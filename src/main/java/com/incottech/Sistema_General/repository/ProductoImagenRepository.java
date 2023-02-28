/**
 * 
 */
package com.incottech.Sistema_General.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.ProductoCompleto;
import com.incottech.Sistema_General.domain.entity.ProductoImagen;


/**
 * @author Rubén Vazquez Acosta
 *
 */
@Repository
public interface ProductoImagenRepository  extends JpaRepository< ProductoCompleto , Long > {
	
	

}
