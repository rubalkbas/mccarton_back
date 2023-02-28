/**
 * 
 */
package com.incottech.Sistema_General.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Rubén Vazquez Acosta
 *
 */
@Getter
@Setter
@ToString
public class ParametrosRegistroUsuario implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	  
    private String username;
 
      
    private String email;
 
    private String password;

	private String apePa;

	private String apeMa;
	
	private String estado;
	
	private String municipio;
	
	private String localidad;
	
	private String tipoZona;
	
	private String codigoPostal;
	
	private String calle;
	
	private String numeroExterior;
	
	private String numeroInterior;

	private String telefono;

	private String identificacion = "";

	private String estatus = "";

	private String notas;

	private Date fechaAlta ;

	private Date fechaBaja;

	private int idUsuario;
}
