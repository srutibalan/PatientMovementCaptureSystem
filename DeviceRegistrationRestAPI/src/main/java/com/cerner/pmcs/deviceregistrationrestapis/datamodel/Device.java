package com.cerner.pmcs.deviceregistrationrestapis.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Device.
 *
 * @author SB051046
 */
@XmlRootElement
public class Device {
	
	private String deviceName = "";
	/** The device id. */
	private String deviceId = "";
	
	/** The input pins. */
	private List<Integer> inputPins = new ArrayList<Integer>();
	
	/** The output pins. */
	private List<Integer> outputPins = new ArrayList<Integer>();
	
	/** The device type. */
	private String deviceType;
	
	/** The registered date. */
	private long registeredDate = 0;
	
	/**
	 * Gets the device name.
	 *
	 * @return the device name
	 */
	public String getDeviceName() {
		return deviceName;
	}
	
	/**
	 * Sets the device name.
	 *
	 * @param deviceName the new device name
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Sets the device id.
	 */
	public void setDeviceId() {
		deviceId = UUID.randomUUID().toString();			//random string generator
	}

	/**
	 * Gets the input pins.
	 *
	 * @return the input pins
	 */
	public List<Integer> getInputPins() {
		return inputPins;
	}

	/**
	 * Sets the input pins.
	 *
	 * @param inputPins the new input pins
	 */
	public void setInputPins(List<Integer> inputPins) {
		this.inputPins = inputPins;
	}

	/**
	 * Gets the output pins.
	 *
	 * @return the output pins
	 */
	public List<Integer> getOutputPins() {
		return outputPins;
	}

	/**
	 * Sets the output pins.
	 *
	 * @param outputPins the new output pins
	 */
	public void setOutputPins(List<Integer> outputPins) {
		this.outputPins = outputPins;
	}

	/**
	 * Gets the device type.
	 *
	 * @return the device type
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * Sets the device type.
	 *
	 * @param deviceType the new device type
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * Gets the registered date.
	 *
	 * @return the registered date
	 */
	public long getRegisteredDate() {
		return registeredDate;
	}

	/**
	 * Sets the registered date.
	 */
	public void setRegisteredDate() {
		registeredDate = System.currentTimeMillis() / 1000 ;			//epoch time for convenience of use
	}



	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((inputPins == null) ? 0 : inputPins.hashCode());
		result = prime * result + ((outputPins == null) ? 0 : outputPins.hashCode());
		result = prime * result + (int) (registeredDate ^ (registeredDate >>> 32));
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (inputPins == null) {
			if (other.inputPins != null)
				return false;
		} else if (!inputPins.equals(other.inputPins))
			return false;
		if (outputPins == null) {
			if (other.outputPins != null)
				return false;
		} else if (!outputPins.equals(other.outputPins))
			return false;
		if (registeredDate != other.registeredDate)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", inputPins=" + inputPins + ", outputPins=" + outputPins
				+ ", deviceType=" + deviceType + ", registeredDate=" + registeredDate + "]";
	}

}
