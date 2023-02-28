package com.incottech.Sistema_General.model;

/**
 * @author Rubén Vazquez Acosta
 *
 */
public class ResponseMessage {
	 private String message;

	  public ResponseMessage(String message) {
	    this.message = message;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
