/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "gen_cat_codigo_postal", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class CatCodigoPostal implements Serializable{
	
private static final long serialVersionUID = 1L;
	
    @Id
	@Column(name = "codigo_postal")
	private String codigoPostal;

	@Column(name = "colonia")
	private String colonia;
	
	@Column(name = "localidad")
	private String localidad;

	@Column(name = "estado")
	private String estado;

	@Column(name = "municipio")
	private String municipio;

	@Column(name = "tipo_asentamiento")
	private String tipo_asentamiento;

	@Column(name = "id_estado")
	private String id_estado;

	@Column(name = "descripcion_zona")
	private String descripcion_zona;

	@Column(name = "ciudad")
	private String ciudad;

}
