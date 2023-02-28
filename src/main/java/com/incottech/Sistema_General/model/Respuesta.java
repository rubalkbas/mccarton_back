package com.incottech.Sistema_General.model;

import java.util.ArrayList;
import java.util.List;

public class Respuesta {
	private static final long serialVersionUID = 1L;

	   /** Retorna el estatus de la peticion o codigo*/
		private String estatus;
		/** Retorna el mensaje de la peticion. */
		private String mensaje = "";
		/** Retorna un valor o cadena string */
		private String valor;
		/** retorna un codigo de error */
		private String codigoError;		
		/** Retorna una lista de DTOs */
		private transient  List<?> lista = null;
		/** Retorna un DTO */
		private transient  Object dto = null;
		
		
		/**
		 * @return the estatus
		 */
		public String getEstatus() {
			return estatus;
		}
		/**
		 * @param estatus the estatus to set
		 */
		public void setEstatus(String estatus) {
			this.estatus = estatus;
		}
		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}
		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		/**
		 * @return the valor
		 */
		public String getValor() {
			return valor;
		}
		/**
		 * @param valor the valor to set
		 */
		public void setValor(String valor) {
			this.valor = valor;
		}
		/**
		 * @return the codigoError
		 */
		public String getCodigoError() {
			return codigoError;
		}
		/**
		 * @param codigoError the codigoError to set
		 */
		public void setCodigoError(String codigoError) {
			this.codigoError = codigoError;
		}
		/**
		 * @return the dto
		 */
		public Object getDto() {
			return dto;
		}
		/**
		 * @param dto the dto to set
		 */
		public void setDto(Object dto) {
			this.dto = dto;
		}
		
		/**
		 * @return the lista
		 */
		public List<?> getLista() {
		    List<?> tmp = null;
		    if(this.lista != null){
		        tmp = new ArrayList<>(this.lista);
		    }
		    
			return tmp;
		}

		/**
		 * @param lista
		 *            the lista to set
		 */
		public void setLista(List<?> tmp) {
		    if(tmp != null){
		        this.lista = new ArrayList<>(tmp);
		    }
		}

}
