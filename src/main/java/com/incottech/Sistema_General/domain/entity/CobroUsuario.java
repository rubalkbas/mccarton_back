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
@Table(name = "gen_cobro_usuario", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class CobroUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COBRO_USUARIO")
	private Long idCobroUsuario;

	@Column(name = "FECHA_EMISION")
	private Date fechaEmision;
	
	@Column(name = "FOLIO_RECIBO")
	private String folioRecibo;
	
	@Column(name = "TOTAL_MOTON_COBRADO")
	private Double totalMontoCobrado;
	
	@Column(name = "ID_USUARIO_CLIENTE")
	private Long idUsuarioCliente;
	
	@Column(name = "ID_USUARIO_EMPLEADO")
	private Long idUsuarioEmpleado;

}
