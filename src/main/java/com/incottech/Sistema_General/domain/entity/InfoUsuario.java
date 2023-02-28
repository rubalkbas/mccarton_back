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
 * @author Rubén Vazquez Acosta
 *
 */

@Entity
@Table(name = "gen_aux_info_usuario", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class InfoUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INFO_USUARIO")
	private Long idInfoUsuario;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APE_PAT")
	private String apellidoPat;
	
	@Column(name = "APE_MAT")
	private String apellidoMaterno;
	
	@Column(name = "URL_IDENTIFICACION")
	private String urlIdentificacion;
	
	@Column(name = "DESCRIPCION_RAPIDA")
	private String descRapida;
	
	@Column(name = "URL_FACE")
	private String urlFace;
	
	@Column(name = "URL_TWIT")
	private int urlTwit;
	
	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;
	
	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@Column(name = "ESTATUS")
	private Long estatus;
	
	@Id
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

}
