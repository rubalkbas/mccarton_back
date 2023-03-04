package com.mccarton.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIOS")
@Getter
@Setter
public class UsuarioEntity implements Serializable{

	/**
	 * Variable para serializar la clase.
	 */
	private static final long serialVersionUID = -8590131624056335066L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO", length = 2)
	private Integer idUsuario;
	
	@Column(name = "NOMBRE", nullable = false, length = 50)
	private String nombreUsuario;
	
	@Column(name = "APELLIDO_PATERNO", nullable = false, length = 30)
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO", length = 30)
	private String apellidoMaterno;
	
	@Column(name = "CORREO_ELECTRONICO", unique = true, length = 50)
	private String correoElectronico;
	
	@Column(name = "PASSWORD", length = 64)
	private String password;
	
	@Column(name = "FOTO", length = 100)
	private String foto;
	
	@Column(name = "ESTATUS", length = 1)
	private Integer estatus;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ID_ROL" , nullable = true)
	@JsonManagedReference
	private RolEntity rol;
	
	

}
