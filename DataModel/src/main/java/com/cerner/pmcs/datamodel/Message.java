/*
 * 
 */
package com.cerner.pmcs.datamodel;


// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message implements java.io.Serializable {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message body. */
    private String messageBody;
    
    /** The created. */
    private long created;
    
    /** The message id. */
    private long messageId;
    
    /** The device id. */
    private String deviceName;
    
    /**
	 * Instantiates a new message.
	 */
    public Message() {
    	this.deviceName= null;
    	this.created = System.currentTimeMillis()/1000;	
    }
    
    /**
	 * Instantiates a new message.
	 *
	 * @param id
	 *            the id
	 * @param message
	 *            the message
	 */
    public Message(long id, String message) {
    	this.messageId = id;
    	this.messageBody = message;
    	this.created = System.currentTimeMillis()/1000;
    	this.deviceName = null;
    }

	/**
	 * Gets the message body.
	 *
	 * @return the message body
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * Sets the message body.
	 *
	 * @param message
	 *            the new message body
	 */
	public void setMessageBody(String message) {
		this.messageBody = message;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public long getCreated() {
		return created;
	}

	/**
	 * Gets the message id.
	 *
	 * @return the message id
	 */
	public long getMessageId() {
		return messageId;
	}
	
	/**
	 * Sets the message id.
	 *
	 * @param id
	 *            the new message id
	 */
	public void setMessageId(long id)
	{
		this.messageId=id;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public String getDeviceName()
	{
			return deviceName;
	}

	/**
	 * Sets the device id.
	 *
	 * @param deviceName
	 *            the new device id
	 */
	public void setDeviceName(String deviceName)
	{
			this.deviceName = deviceName;
	}

	

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
	public String toString()
	{
		return "Message [messageBody=" + messageBody + ", created=" + created + ", messageId=" + messageId
				+ ", deviceName=" + deviceName + "]";
	}
}


