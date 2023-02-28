/**
 * 
 */
package com.incottech.Sistema_General.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.incottech.Sistema_General.domain.entity.CatCodigoPostal;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Repository
public interface CatalogosRepository extends JpaRepository< CatCodigoPostal ,Long>{
	
	@Query(value = "select * from bdgeneral.gen_cat_codigo_postal where  trim(codigo_postal) =  trim(:cp) ", nativeQuery = true)
    List <CatCodigoPostal> consultaPorCP(@Param("cp") String cp);

}
