package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;

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
@Table(name = "gen_estado_cuenta_usuario", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class EstadoCuentaUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ESTADO_CUENTA_USUARIO")
	private Long idEstadoCuentaUsuario;

	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name = "SALDO")
	private Double saldo;

}
