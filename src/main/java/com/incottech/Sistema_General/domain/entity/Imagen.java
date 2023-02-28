package com.incottech.Sistema_General.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Eduardo Nuñez
 * @version 1.0
 * @since   2020-12-28
 */

@Getter
@Setter
@NoArgsConstructor
public class Imagen implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "SMALL")
	private String small;
	
	@Column(name = "MEDIUM")
	private String medium;
	
	@Column(name = "BIG")
	private String big;

}
