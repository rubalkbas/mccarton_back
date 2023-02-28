package com.incottech.Sistema_General.payload.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String correo;

	@NotBlank
	private String pass;

}
