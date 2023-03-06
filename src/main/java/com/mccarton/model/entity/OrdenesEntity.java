package com.mccarton.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "ORDENES")
@Getter
@Setter


public class OrdenesEntity implements Serializable{

	
	/**
	 * Variable para serializar la clase
	 */
	private static final long serialVersionUID = -1749347161750703031L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ORDEN", length = 1)
	private Integer idOrden;
	
	@Column(name = "TOTAL", length = 10)
	private Double total;
	
	@Column(name = "FECHA", nullable = false)
	private LocalDateTime fechaOrden;
	
	/**
	 * Relación Uno a Muchos
	 * Lista de asuntos
	 * */
	@OneToMany(mappedBy = "orden")
	@JsonBackReference
	private List<OrdenDetalleEntity> ordene_detalle;
}
