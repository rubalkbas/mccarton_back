/**
 * 
 */
package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @author rubal
 *
 */
@Entity
@Table(name = "ps_mx_mae_asuntos", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class AsuntosEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ASUNTO")
	private Long idAsunto;

	@Column(name = "TXT_NOMB")
	private String txtNombre;
	
	@Column(name = "ID_PRIOR_USR")
	private Long isPriosUser;
	
	@Column(name = "NUM_TEL")
	private Long numTel;
	
	@Column(name = "NUM_TEL_CEL")
	private Long numTelCel;
	
	@Column(name = "ID_USR_CREAC")
	private Long idUserCreacion;
	
	@Column(name = "ID_PRIOR_SYS")
	private Long isPriosSis;
	
	@Column(name = "ID_GRUP_RESOL")
	private Long isGrupResol;
	
	@Column(name = "ID_RESOL")
	private Long isResol;
	
	@Column(name = "NUM_EXTER")
	private Long numExterno;
	
	@Column(name = "NUM_ORDEN_SERV")
	private Long numOrdenServicio;

	@Column(name = "TXT_MAIL")
	private String txtMail;
	

	@Column(name = "TXT_CLAS_ASUNT")
	private String txtClasificacionAsunto;

	@Column(name = "TXT_GEST_ESPEC")
	private String txtGestEspec;

	@Column(name = "TXT_DSC_DETA")
	private String txtDescripcionDetalle;

	@Column(name = "FCH_CREAC")
	private Timestamp fchCreacion;

	@Column(name = "FCH_COMPR_SOLUC")
	private Timestamp fchCompromisoSolucion;
	
	
	
	

}
