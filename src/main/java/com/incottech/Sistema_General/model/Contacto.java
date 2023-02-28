package com.incottech.Sistema_General.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contacto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String message;
	
}
