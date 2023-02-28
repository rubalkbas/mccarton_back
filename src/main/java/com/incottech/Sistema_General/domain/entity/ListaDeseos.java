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

@Entity
@Table(name = "gen_lista_deseos", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class ListaDeseos implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LISTA_DESEO")
	private Long idListaDeseo;

	@Column(name= "ESTATUS")
	private Long estatus;
	
	@Column(name= "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name= "ID_PRODUCTO")
	private Long idProducto;
	
	@Column(name= "FECHA_REGISTRO")
	private Date fechaRegistro;
	

	
}
