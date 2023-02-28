/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

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
 * @author Rubalkbas
 *
 */
@Entity
@Table(name = "gen_aux_domicilio_usuario", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class domicilioUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comicilio_usuario")
	private Long idDomUsuario;
	
	@Column(name = "NOMBRE_DOMICILIO")
	private String nombreDomicilio;
	
	@Column(name = "codigo_postal")
	private String cp;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "delegacion")
	private String delegacion;
	
	@Column(name = "asentamiento")
	private String asentamiento;
	
	@Column(name = "calle")
	private String calle;
	
	@Column(name = "numero_exterior")
	private String noExt;
	
	@Column(name = "numero_interior")
	private String noInt;
	
	@Column(name = "entre_calle_1")
	private String entreCalle1;
	
	@Column(name = "entre_calle_2")
	private String entreCalle2;
	
	@Column(name = "indicaciones")
	private String indicaciones;
	
	@Column(name = "tipo_domicilio")
	private String tipoDom;
	
	@Column(name = "estatus")
	private String estatus;
	
	@Column(name = "fch_alta")
	private String fchAlta;


	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "domicilio_predeterminado")
	private Long domPrede;
}
