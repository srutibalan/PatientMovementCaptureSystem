package com.cerner.pmcs.datamodel;

/**
 * The Class ErrorMessage.
 */
public class ErrorMessage {

	/** The error message. */
	private String errorMessage;
	
	/** The error code. */
	private int errorCode;
	
	/**
	 * Instantiates a new error message.
	 */
	public ErrorMessage() {
		
	}
		
	/**
	 * Instantiates a new error message.
	 *
	 * @param errorMessage
	 *            the error message
	 * @param errorCode
	 *            the error code
	 */
	public ErrorMessage(String errorMessage, int errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * Sets the error message.
	 *
	 * @param errorMessage
	 *            the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
