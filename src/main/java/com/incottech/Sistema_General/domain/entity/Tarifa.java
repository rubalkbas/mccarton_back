package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo N
 *
 */

@Entity
@Table(name = "gen_tarifa", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class Tarifa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TARIFA")
	private Long idTarifa;

	@Column(name = "AUTOMATICO")
	private int automatico;
	
	@Column(name = "COSTO")
	private Double costo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ESTATUS")
	private Long estatus;
	
	@Column(name = "FECHA_COBRO")
	private Date fechaCobro;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private Date fechaFin;
	
	@Column(name = "MULTA_INCUMPLIMIENTO")
	private Double multaIncumplimiento;

}
