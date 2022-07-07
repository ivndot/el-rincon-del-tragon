package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject;

import java.io.Serializable;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */

public class ErrorValueObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String description;
	private Exception exception;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
