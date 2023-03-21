package com.mccarton.model.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mccarton.model.entity.ClienteEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDireccion{


	private Integer idDireccion;
	
	private String calle;
	
	private String numeroExterior;
	
	private String numeroInterior;
	
	private Integer codigoPostal;
	
	private String nombreDireccion;
	
	private String colonia;
	
	private String entreCalle1;
	
	private String entreCalle2;
	
	private Integer predeterminado;
	
	private Integer estatus;
	
	private String ciudad;
	
	private String telefono;
	
	private ClienteEntity cliente;
	
	private Integer idCliente;
	
	private String correoElectronico;
	
	private String password;
	
	private String nombre;
	
	private String apellidoPaterno;
	
	private String apellidoMaterno;
	
}
