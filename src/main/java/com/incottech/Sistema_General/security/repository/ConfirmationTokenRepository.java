/**
 * 
 */
package com.incottech.Sistema_General.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incottech.Sistema_General.domain.entity.ConfirmationToken;


/**
 * @author Rubén Vazquez Acosta
 *
 */
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken , Long>{
	 @Query(value = "SELECT * FROM bdgeneral.gen_aux_confirmacion_token where confirmation_token = :token", nativeQuery = true)
	 ConfirmationToken findByConfirmationToken(@Param("token") String confirmationTokens);
		
}
