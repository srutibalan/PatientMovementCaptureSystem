package com.cerner.pmcs.datamodel;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Device.
 *
 * @author SB051046
 */

public class Device {

	/** The device id. */
	private long deviceId;

	/** The input pins. */
	private List<Integer> inputPins = new ArrayList<Integer>();

	/** The output pins. */
	private List<Integer> outputPins = new ArrayList<Integer>();

	/** The device type. */
	private String deviceType;

	/** The registred date. */
	private long registeredDate;


	/**
	 * Instantiates a new device.
	 *
	 * @param builder
	 *            the builder
	 */

	public Device(DeviceBuilder builder) {
		deviceId = builder.deviceId;
		inputPins = builder.inputPins;
		outputPins = builder.outputPins;
		deviceType = builder.deviceType;
		registeredDate = builder.registeredDate;
	}

	/**
	 * Gets the device id.
	 *
	 * @return the device id
	 */
	public long getDeviceId() {
		return deviceId;
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
	 * Gets the output pins.
	 *
	 * @return the output pins
	 */
	public List<Integer> getOutputPins() {
		return outputPins;
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
	 * Gets the registred date.
	 *
	 * @return the registred date
	 */
	public long getRegisteredDate() {
		return registeredDate;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (deviceId ^ (deviceId >>> 32));
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
		if (deviceId != other.deviceId)
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
