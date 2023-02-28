/**
 * 
 */
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
@Table(name = "gen_aux_usuario_info", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class UsuariosInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuarios_info")
	public Long id;
    

	@Column(name = "nombre")
	private String nombreUsuario = "";

	@Column(name = "ape_p")
	private String apePa;

	@Column(name = "ape_m")
	private String apeMa;

	@Column(name = "estado")
	private String estado = "";
	
	@Column(name = "municipio")
	private String municipio = "";
	
	@Column(name = "localidad")
	private String localidad = "";
	
	@Column(name = "tipo_zona")
	private String tipoZona = "";
	
	@Column(name = "codigo_postal")
	private String codigoPostal = "";
	
	@Column(name = "calle")
	private String calle = "";
	
	@Column(name = "numero_exterior")
	private String numeroExterior = "";
	
	@Column(name = "numero_interior")
	private String numeroInterior = "";
	

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "identificacion")
	private String identificacion = "";

	@Column(name = "estatus")
	private String estatus = "";

	@Column(name = "notas")
	private String notas;

	@Column(name = "fecha_alta")
	private Date fechaAlta ;

	@Column(name = "fecha_baja")
	private Date fechaBaja;
	
	@Column(name = "id_usuario")
	private Long idUsuario;


}
