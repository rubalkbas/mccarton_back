package com.mccarton.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
public class ClienteEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7044564663461049158L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE", length = 6)
	private Integer idCliente;
	
	@Column(name = "CORREO_ELECTRONICO", unique = true, nullable = false, length = 50)
	private String correoElectronico;
	
	@Column(name = "PASSWORD", nullable = false, length = 64)
	private String password;
	
	@Column(name = "NOMBRE", nullable = false, length = 50)
	private String nombre;
	
	@Column(name = "APELLIDO_PATERNO", nullable = false, length = 30)
	private String apellidoPaterno;
	
	@Column(name = "APELLIDO_MATERNO",  length = 30)
	private String apellidoMaterno;
	
	@Column(name = "TELEFONO", nullable = false, length = 10)
	private String telefono;
	
	@Column(name = "ESTATUS", nullable = false, length = 1)
	private Integer estatus;
	
	/**
	 * Relación Uno a Muchos
	 * Lista de asuntos
	 * */
	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	private List<DireccionEntity> direcciones;
	
	/**
	 * Relación Uno a Muchos
	 * Lista de asuntos
	 * */
	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	private List<CarroComprasEntity> carroCompras;
	
	/**
	 * Relación Uno a Muchos
	 * Lista de asuntos
	 * */
	@OneToMany(mappedBy = "cliente")
	@JsonBackReference
	private List<ListaDeseosEntity> listaDeseos;


	

}
