/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

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
@Table(name = "gen_mae_categoria", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class MaestraCategoria {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idcategoria;

	@Column(name = "desc_categoria")
	private String descCategoria;
	
	@Column(name = "nivel_categoria")
	private Integer nivelcategoria;
	
	@Column(name = "nivel_padre")
	private Integer nivelPadre;
	
	
	@Column(name = "estatus")
	private String estatus;
	
}
