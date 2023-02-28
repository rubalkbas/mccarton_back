package com.incottech.Sistema_General.model;

import java.util.Date;

import javax.persistence.Column;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrdenCompraCompleta {
	
	private Long idOrdenCompra;

	private Long idUsuario;

	private Date fechaCompra;

	private Double total;

	private Double iva;

	private int totalDescuento;

	private Long idMotDescuento;

	private String estatusOrdenCompra;

	private String codigoRastreo;

}
