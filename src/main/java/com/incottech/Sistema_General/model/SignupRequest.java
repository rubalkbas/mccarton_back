/**
 * 
 */
package com.incottech.Sistema_General.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rubén Vazquez Acosta
 *
 */
public class SignupRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
    @Size(min = 3, max = 20)
	@Getter
	@Setter	    
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
	@Getter
	@Setter	    
    private String email;
    
    /*private Set<String> role;*/
    
    @NotBlank
    @Size(min = 6, max = 40)
	@Getter
	@Setter	    
    private String password;

}
