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

@Entity
@Table(name = "gen_aux_img_banner", schema = "bdgeneral" )
@Getter
@Setter
@NoArgsConstructor
public class AuxImagenBanner implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_IMG_BANNER")
	private Long idImgBanner;

	@Column(name = "ESTATUS")
	private int estatus;
	
	@Column(name = "SUBTITULO")
	private String subtitulo;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "URL_IMG")
	private String urlImg;

}
