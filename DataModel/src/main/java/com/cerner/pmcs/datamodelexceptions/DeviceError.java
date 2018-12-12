
package com.cerner.pmcs.datamodelexceptions;

/**
 * The Class DeviceError.
 */
public class DeviceError {
	
	/** The error message. */
	private String errorMessage;
	
	/** The error code. */
	private int errorCode;
	
	/**
	 * Instantiates a new device error.
	 */
	public DeviceError() {
		
	}
	
	/**
	 * Instantiates a new device error.
	 *
	 * @param newErrorMessage
	 *            the new error message
	 * @param newErrorCode
	 *            the new error code
	 */
	public DeviceError(String newErrorMessage, int newErrorCode) {
		errorMessage = newErrorMessage;
		errorCode = newErrorCode;
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
	 * @param newErrorMessage
	 *            the new error message
	 */
	public void setErrorMessage(String newErrorMessage) {
		errorMessage = newErrorMessage;
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
	 * @param newErrorCode
	 *            the new error code
	 */
	public void setErrorCode(int newErrorCode) {
		errorCode = newErrorCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + errorCode;
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceError other = (DeviceError) obj;
		if (errorCode != other.errorCode)
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceError [errorMessage=" + errorMessage + ", errorCode=" + errorCode + "]";
	}

	
}
