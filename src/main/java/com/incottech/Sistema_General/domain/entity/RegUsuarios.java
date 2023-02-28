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
@Table(name = "gen_mae_usuarios", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class RegUsuarios  implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	public Long id;
    

	@Column(name = "NOMBRE_USUARIO")
	private String nombreUsuario = "";

	@Column(name = "CORREO")
	private String correo;

	@Column(name = "PASS")
	private String pass;

	@Column(name = "CADENA")
	private String cadena = "";

	@Column(name = "ESTATUS")
	private String estatus;

	@Column(name = "FECHA_ALTA")
	private Date fechaAlta;

	@Column(name = "FECHA_BAJA")
	private Date fechaBaja;
	
	@Column(name = "CONFIRMA_CORREO")
	private int confirmaCorreo;


	public RegUsuarios(String nombreUsuario, String correo, String pass) {
		this.nombreUsuario = nombreUsuario;
		this.correo = correo;
		this.pass = pass;
	}
}
